package com.icuxika.scaffold.module.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icuxika.scaffold.annotation.ApiReturn;
import com.icuxika.scaffold.module.auth.entity.GitHubAccessTokenValue;
import com.icuxika.scaffold.module.auth.entity.GitHubUserInfo;
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
@RequestMapping("/auth/github")
public class GitHubOAuth2Controller {

    private static final String CLIENT_ID = "4efe670ba6206abdf3ad";

    private static final String CLIENT_SECRETS = "a4dd39dfa16526b536bbde457d7139a2cfbfdab7";

    private static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    private static final String GITHUB_USER_URL = "https://api.github.com/user";

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
                    .uri(URI.create(GITHUB_ACCESS_TOKEN_URL + "?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRETS + "&code=" + code))
                    .header("Accept", "application/json")
                    .build();
            HttpResponse<String> responseForAccessToken = client.send(request, HttpResponse.BodyHandlers.ofString());
            GitHubAccessTokenValue accessTokenValue = objectMapper.readValue(responseForAccessToken.body(), GitHubAccessTokenValue.class);
            System.out.println(accessTokenValue);

            // 获取用户信息
            request = HttpRequest.newBuilder()
                    .uri(URI.create(GITHUB_USER_URL))
                    .header("Authorization", "token " + accessTokenValue.getAccessToken())
                    .build();
            HttpResponse<String> responseForUserInfo = client.send(request, HttpResponse.BodyHandlers.ofString());
            GitHubUserInfo userInfo = objectMapper.readValue(responseForUserInfo.body(), GitHubUserInfo.class);
            System.out.println(userInfo);
            context.setVariable(TEMPLATE_CALLBACK_VALUE_TOKEN, CLIENT_ID);
            return templateEngine.process(TEMPLATE_CALLBACK, context);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return templateEngine.process(TEMPLATE_ERROR, context);
    }

}
