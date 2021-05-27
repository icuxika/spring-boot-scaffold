package com.icuxika.scaffold.module.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icuxika.scaffold.annotation.ApiReturn;
import com.icuxika.scaffold.module.auth.entity.GiteeAccessTokenValue;
import com.icuxika.scaffold.module.auth.entity.GiteeUserInfo;
import com.icuxika.scaffold.module.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/auth/gitee")
public class GiteeOAuth2Controller {

    private static final String GITEE_ACCESS_TOKEN_URL = "https://gitee.com/oauth/token";

    private static final String GITEE_USER_URL = "https://gitee.com/api/v5/user";

    private static final String TEMPLATE_NAME_CALLBACK = "callback";

    private static final String TEMPLATE_NAME_ERROR = "errorMsg";

    private static final String TEMPLATE_CALLBACK_VALUE_TOKEN = "token";

    private static final String TEMPLATE_CALLBACK_VALUE_NOTIFICATION_PAGE_URL = "notificationPageUrl";

    @Value("${oauth2.gitee.client-id}")
    private String clientId;

    @Value("${oauth2.gitee.client-secret}")
    private String clientSecrets;

    @Value("${oauth2.notification-page-url}")
    private String notificationPageUrl;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private AuthService authService;

    @ApiReturn(disable = true)
    @RequestMapping("/callback")
    public String callback(String code, HttpSession session) {
        Context context = new Context();
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .uri(URI.create(GITEE_ACCESS_TOKEN_URL + "?grant_type=authorization_code&code=" + code + "&client_id=" + clientId + "&client_secret=" + clientSecrets + "&redirect_uri=http://127.0.0.1:8888/auth/gitee/callback"))
                    .build();
            HttpResponse<String> responseForAccessToken = client.send(request, HttpResponse.BodyHandlers.ofString());
            GiteeAccessTokenValue accessTokenValue = objectMapper.readValue(responseForAccessToken.body(), GiteeAccessTokenValue.class);
            System.out.println(accessTokenValue);

            // 获取用户信息
            request = HttpRequest.newBuilder()
                    .uri(URI.create(GITEE_USER_URL + "?access_token=" + accessTokenValue.getAccessToken()))
                    .build();
            HttpResponse<String> responseForUserInfo = client.send(request, HttpResponse.BodyHandlers.ofString());
            GiteeUserInfo userInfo = objectMapper.readValue(responseForUserInfo.body(), GiteeUserInfo.class);
            System.out.println(userInfo);

            authService.loginWithGitee(userInfo, session);

            context.setVariable(TEMPLATE_CALLBACK_VALUE_TOKEN, session.getId());
            context.setVariable(TEMPLATE_CALLBACK_VALUE_NOTIFICATION_PAGE_URL, notificationPageUrl);
            return templateEngine.process(TEMPLATE_NAME_CALLBACK, context);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return templateEngine.process(TEMPLATE_NAME_ERROR, context);
    }
}
