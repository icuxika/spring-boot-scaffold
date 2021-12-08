package com.icuxika.scaffold.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccessDecisionManagerImpl implements AccessDecisionManager {

    private static final Logger logger = LoggerFactory.getLogger(AccessDecisionManagerImpl.class);

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (authentication == null) {
            throw new AccessDeniedException("Access is denied");
        }
        List<String> requiredAuthorities = configAttributes.stream().map(ConfigAttribute::getAttribute).collect(Collectors.toList());
        // 如果请求需要的权限是ROLE_LOGIN，则判断authentication是不是AnonymousAuthenticationToken
        // 是的话，则是FilterInvocationSecurityMetadataSourceImpl传递过来的，表示用户没有登录
        // 不是的话，则表示当前请求只需要登录即可访问
        if (requiredAuthorities.contains("ROLE_LOGIN")) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                throw new BadCredentialsException("未登录");
            } else {
                return;
            }
        }
        List<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        if (authorities.retainAll(requiredAuthorities)) {
            return;
        }
        logger.info("需要的权限：" + requiredAuthorities);
        logger.info("用户拥有的权限：" + authorities);
        throw new AccessDeniedException("Access is denied");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
