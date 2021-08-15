package com.icuxika.scaffold.security;

import com.icuxika.scaffold.module.auth.entity.UserToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 需要token验证的请求需携带此请求头来验证token数据
     */
    public static final String HEADER_X_AUTH_TOKEN = "X-Auth-Token";

    /**
     * 登录成功时构造数据以此key交由Spring Session存储于Redis之中
     */
    public static final String SESSION_ATTRIBUTE_USER_TOKEN = "user-token";

    private final UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 获取请求头中的token
        String token = ((HttpServletRequest) request).getHeader(HEADER_X_AUTH_TOKEN);
        // 获取token对应的用户数据
        UserToken userToken = (UserToken) httpServletRequest.getSession().getAttribute(SESSION_ATTRIBUTE_USER_TOKEN);

        if (token == null || userToken == null) {
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.getWriter().write("No token");
            httpServletResponse.flushBuffer();
            return;
        } else {
            // 登录凭证过期
            if (userToken.getExpireIn().isBefore(LocalDateTime.now())) {
                httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                httpServletResponse.getWriter().write("Login credentials expired");
                httpServletResponse.flushBuffer();
                return;
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername("icuxika");
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }
}
