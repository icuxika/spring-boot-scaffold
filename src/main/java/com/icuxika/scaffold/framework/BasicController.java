package com.icuxika.scaffold.framework;

import com.icuxika.scaffold.module.auth.entity.UserToken;
import com.icuxika.scaffold.security.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class BasicController {

    @Autowired
    private HttpSession session;

    /**
     * 当前登录用户的id
     */
    public long currentUserId() {
        return ((UserToken) session.getAttribute(TokenAuthenticationFilter.SESSION_ATTRIBUTE_USER_TOKEN)).getUserId();
    }

}
