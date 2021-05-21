package com.icuxika.scaffold.module.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icuxika.scaffold.annotation.ApiReturn;
import com.icuxika.scaffold.module.auth.entity.GiteeAccessTokenValue;
import com.icuxika.scaffold.module.auth.entity.GiteeUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/auth/gitee")
public class GiteeOAuth2Controller {

    private static final String CLIENT_ID = "6f4427cc93279d39b0c8334c99534ee21f7053aef3bc9c9f678004e6ece840a3";

    private static final String CLIENT_SECRETS = "2f013b690a3d5db1edfd504be1a102afa9f4d49a46acb80a8a048a50b6663896";

    private static final String GITEE_ACCESS_TOKEN_URL = "https://gitee.com/oauth/token";

    private static final String GITEE_USER_URL = "https://gitee.com/api/v5/user";

    private static final String TEMPLATE_CALLBACK = "callback";

    private static final String TEMPLATE_CALLBACK_VALUE_TOKEN = "token";

    private static final String TEMPLATE_ERROR = "error";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TemplateEngine templateEngine;

    @ApiReturn(disable = true)
    @RequestMapping("/callback")
    public String callback(String code) {
        Context context = new Context();
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .uri(URI.create(GITEE_ACCESS_TOKEN_URL + "?grant_type=authorization_code&code=" + code + "&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRETS + "&redirect_uri=http://127.0.0.1:8888/auth/gitee/callback"))
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
            context.setVariable(TEMPLATE_CALLBACK_VALUE_TOKEN, CLIENT_ID);
            return templateEngine.process(TEMPLATE_CALLBACK, context);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return templateEngine.process(TEMPLATE_ERROR, context);
    }
}
