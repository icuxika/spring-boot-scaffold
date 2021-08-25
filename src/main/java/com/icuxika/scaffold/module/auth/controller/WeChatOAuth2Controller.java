package com.icuxika.scaffold.module.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icuxika.scaffold.config.ApiData;
import com.icuxika.scaffold.framework.BasicController;
import com.icuxika.scaffold.module.auth.entity.EncryptedWeChatPhoneData;
import com.icuxika.scaffold.module.auth.entity.UserToken;
import com.icuxika.scaffold.module.auth.entity.WeChatPhone;
import com.icuxika.scaffold.module.auth.entity.WeChatUserInfo;
import com.icuxika.scaffold.module.auth.service.AuthService;
import com.icuxika.scaffold.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/auth/wechat")
public class WeChatOAuth2Controller extends BasicController {

    private static final String WECHAT_AUTH_URL = "https://api.weixin.qq.com/sns/jscode2session";

    private static final String WECHAT_APP_ID = "wx723a172c9df27e98";

    private static final String WECHAT_SECRET = "cfe301b66f9e9462b3c3ac0581d3dc01";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("/login")
    public void login(String code, HttpSession session) {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(WECHAT_AUTH_URL + "?appid=" + WECHAT_APP_ID + "&secret=" + WECHAT_SECRET + "&js_code=" + code + "&grant_type=authorization_code"))
                    .build();
            System.out.println(request.uri().toString());
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            WeChatUserInfo userInfo = objectMapper.readValue(response.body(), WeChatUserInfo.class);
            UserToken userToken = authService.loginWithWeChat(userInfo, session);
            redisTemplate.opsForHash().put("wechat-session", userToken.getUserId().toString(), userInfo.getSessionKey());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密微信手机号 https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/getPhoneNumber.html
     */
    @PostMapping("/getPhoneNumber")
    public ApiData<WeChatPhone> getPhoneNumber(@RequestBody EncryptedWeChatPhoneData data) {
        String key = (String) redisTemplate.opsForHash().get("wechat-session", String.valueOf(currentUserId()));
        try {
            String result = AESUtil.AESDecryptWeChatPhone(data.getEncryptedData(), key, data.getIv());
            return ApiData.ok(objectMapper.readValue(result, WeChatPhone.class), "查询成功");
        } catch (Exception e) {
            return ApiData.error(null, "微信手机号数据解密过程中出现错误");
        }
    }
}
