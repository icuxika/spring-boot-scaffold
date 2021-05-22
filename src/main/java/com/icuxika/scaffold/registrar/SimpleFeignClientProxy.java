package com.icuxika.scaffold.registrar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icuxika.scaffold.annotation.SimpleRequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimpleFeignClientProxy implements InvocationHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String baseUrl;

    public SimpleFeignClientProxy(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SimpleRequestMapping simpleRequestMapping = method.getAnnotation(SimpleRequestMapping.class);

        String path = simpleRequestMapping.path();
        RequestMethod requestMethod = simpleRequestMapping.method();

        Class<?> returnType = method.getReturnType();
        String url = buildUrl(path, args);
        System.out.println(url);

        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request;

        String result = null;
        switch (requestMethod) {
            case GET -> {
                request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                result = response.body();
            }

            case POST -> {
                request = HttpRequest.newBuilder()
                        .POST(HttpRequest.BodyPublishers.ofString(""))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                result = response.body();
            }
        }

        return result;
    }

    private String buildUrl(String path, Object[] args) {
        System.out.println(path);
        System.out.println(args);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(baseUrl);
        if (path.startsWith("/")) {
            if (baseUrl.endsWith("/")) {
                stringBuilder.append(path, 1, path.length() - 1);
            } else {
                stringBuilder.append(path);
            }
        } else {
            if (!baseUrl.endsWith("/")) {
                stringBuilder.append("/");
            }
            stringBuilder.append(path);
        }
        return stringBuilder.toString();
    }
}
