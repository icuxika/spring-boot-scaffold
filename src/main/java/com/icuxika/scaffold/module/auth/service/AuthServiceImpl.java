package com.icuxika.scaffold.module.auth.service;

import com.icuxika.scaffold.annotation.RedisLock;
import com.icuxika.scaffold.exception.ServiceException;
import com.icuxika.scaffold.module.auth.entity.*;
import com.icuxika.scaffold.module.user.entity.LocalAuth;
import com.icuxika.scaffold.module.user.entity.ThirdAuth;
import com.icuxika.scaffold.module.user.entity.User;
import com.icuxika.scaffold.module.user.entity.UserAuth;
import com.icuxika.scaffold.module.user.mapper.*;
import com.icuxika.scaffold.security.TokenAuthenticationFilter;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private LocalAuthMapper localAuthMapper;

    @Autowired
    private ThirdAuthMapper thirdAuthMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void loginWithGitHub(GitHubUserInfo userInfo, HttpSession session) {
        thirdLogin(session, LoginType.GITHUB, userInfo.getId(), userInfo.getLogin(), userInfo.getAvatarUrl());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void loginWithGitee(GiteeUserInfo userInfo, HttpSession session) {
        thirdLogin(session, LoginType.GITEE, userInfo.getId(), userInfo.getName(), userInfo.getAvatarUrl());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserToken login(String username, String password) {
        Optional<LocalAuth> optionalLocalAuth = localAuthMapper.selectOne(s ->
                s.where().where(LocalAuthDynamicSqlSupport.username, SqlBuilder.isEqualTo(username))
        );
        if (optionalLocalAuth.isPresent()) {
            LocalAuth localAuth = optionalLocalAuth.get();
            if (passwordEncoder.matches(password, localAuth.getPassword())) {
                Optional<UserAuth> optionalUserAuth = userAuthMapper.selectOne(s ->
                        s.where()
                                .where(UserAuthDynamicSqlSupport.authId, SqlBuilder.isEqualTo(localAuth.getId()))
                                .and(UserAuthDynamicSqlSupport.type, SqlBuilder.isEqualTo(AuthType.LOCAL.getIndex()))
                );
                if (optionalUserAuth.isPresent()) {
                    UserAuth userAuth = optionalUserAuth.get();
                    UserToken userToken = new UserToken();
                    userToken.setUserId(userAuth.getUserId());
                    userToken.setUsername(localAuth.getUsername());
                    userToken.setExpireIn(LocalDateTime.now().plusMonths(1));
                    return userToken;
                } else {
                    throw new ServiceException("????????????");
                }
            } else {
                throw new ServiceException("???????????????????????????");
            }
        } else {
            throw new ServiceException("???????????????????????????");
        }
    }

    @RedisLock(key = "auth-register")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserToken register(String username, String password) {
        Optional<LocalAuth> optionalLocalAuth = localAuthMapper.selectOne(s ->
                s.where().where(LocalAuthDynamicSqlSupport.username, SqlBuilder.isEqualTo(username))
        );
        if (optionalLocalAuth.isPresent()) {
            throw new ServiceException("?????????????????????");
        } else {
            LocalAuth localAuth = new LocalAuth();
            localAuth.setUsername(username);
            localAuth.setPassword(passwordEncoder.encode(password));
            localAuth.setPhone("12345678910");
            localAuthMapper.insert(localAuth);

            User user = new User();
            userMapper.insert(user);

            UserAuth userAuth = new UserAuth();
            userAuth.setUserId(user.getId());
            userAuth.setAuthId(localAuth.getId());
            userAuth.setType(AuthType.LOCAL.getIndex());
            userAuthMapper.insert(userAuth);

            UserToken userToken = new UserToken();
            userToken.setUserId(user.getId());
            userToken.setUsername(username);
            userToken.setExpireIn(LocalDateTime.now().plusMonths(1));
            return userToken;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserToken loginWithWeChat(WeChatUserInfo userInfo, HttpSession session) {
        return thirdLogin(session, LoginType.WECHAT, userInfo.getOpenid(), "", "");
    }

    /**
     * ???????????????????????????????????????????????????????????????????????????
     * ?????????????????????
     * 1??????????????????????????????????????????????????????????????????????????????????????????????????????
     * 2?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
     *
     * @param session   ??????Spring session???????????????
     * @param loginType ?????????????????????
     * @param openId    ?????????????????????id
     * @param name      ?????????????????????
     * @param avatar    ?????????????????????
     */
    public UserToken thirdLogin(HttpSession session, LoginType loginType, String openId, String name, String avatar) {
        UserToken userToken = new UserToken();
        thirdAuthMapper.selectOne(s ->
                s.where()
                        .where(ThirdAuthDynamicSqlSupport.openId, SqlBuilder.isEqualTo(openId))
                        .and(ThirdAuthDynamicSqlSupport.type, SqlBuilder.isEqualTo(loginType.getIndex()))
        ).ifPresentOrElse(thirdAuth -> userAuthMapper.selectOne(s ->
                s.where()
                        .where(UserAuthDynamicSqlSupport.authId, SqlBuilder.isEqualTo(thirdAuth.getId()))
                        .and(UserAuthDynamicSqlSupport.type, SqlBuilder.isEqualTo(AuthType.THIRD.getIndex()))
        ).flatMap(userAuth -> userMapper.selectOne(s ->
                s.where().where(UserDynamicSqlSupport.id, SqlBuilder.isEqualTo(userAuth.getUserId()))
        )).ifPresent(user -> userAuthMapper.selectOne(s ->
                s.where()
                        .where(UserAuthDynamicSqlSupport.userId, SqlBuilder.isEqualTo(user.getId()))
                        .and(UserAuthDynamicSqlSupport.type, SqlBuilder.isEqualTo(AuthType.LOCAL.getIndex()))
        ).flatMap(userAuth -> localAuthMapper.selectOne(s ->
                s.where().where(LocalAuthDynamicSqlSupport.id, SqlBuilder.isEqualTo(userAuth.getAuthId()))
        )).ifPresent(localAuth -> {
            userToken.setUserId(user.getId());
            userToken.setUsername(localAuth.getUsername());
            userToken.setExpireIn(LocalDateTime.now().plusMonths(1));
        })), () -> {
            ThirdAuth thirdAuth = new ThirdAuth();
            thirdAuth.setOpenId(openId);
            thirdAuth.setType(loginType.getIndex());
            thirdAuthMapper.insert(thirdAuth);

            User user = new User();
            user.setNickname(name);
            user.setAvatar(avatar);
            userMapper.insert(user);

            UserAuth userAuthForThird = new UserAuth();
            userAuthForThird.setUserId(user.getId());
            userAuthForThird.setAuthId(thirdAuth.getId());
            userAuthForThird.setType(AuthType.THIRD.getIndex());
            userAuthMapper.insert(userAuthForThird);

            String username = loginType + "_" + openId;
            LocalAuth localAuth = new LocalAuth();
            localAuth.setUsername(username);
            localAuth.setPassword(Base64.getEncoder().encodeToString(username.getBytes()) + "-" + UUID.randomUUID());
            localAuth.setPhone("12345678910");
            localAuthMapper.insert(localAuth);

            UserAuth userAuthForLocal = new UserAuth();
            userAuthForLocal.setUserId(user.getId());
            userAuthForLocal.setAuthId(localAuth.getId());
            userAuthForLocal.setType(AuthType.LOCAL.getIndex());
            userAuthMapper.insert(userAuthForLocal);

            userToken.setUserId(user.getId());
            userToken.setUsername(username);
            userToken.setExpireIn(LocalDateTime.now().plusMonths(1));
        });
        session.setAttribute(TokenAuthenticationFilter.SESSION_ATTRIBUTE_USER_TOKEN, userToken);
        return userToken;
    }
}
