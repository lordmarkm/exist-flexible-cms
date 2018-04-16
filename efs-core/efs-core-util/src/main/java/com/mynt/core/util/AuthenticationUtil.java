package com.mynt.core.util;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 *
 * @author mbmartinez, Sep 27, 2017
 *
 */
public class AuthenticationUtil {
    private static final String ANONYMOUS_USER = "anonymousUser";
    private static final String SYSTEM_USER = "SYSTEM";

    public static boolean isAuthenticated() {
        return null != SecurityContextHolder.getContext().getAuthentication()
                && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public static String getLoggedInUsername() {
        if (null == SecurityContextHolder.getContext().getAuthentication()
                || ANONYMOUS_USER.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            return SYSTEM_USER;
        } else {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        }
    }

    public static boolean isAuthorized(String role) {
        for (GrantedAuthority auth : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            if (auth.getAuthority().equals(role)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAuthorized(String role, Collection<? extends  GrantedAuthority> authorities) {
        for (GrantedAuthority auth : authorities) {
            if (auth.getAuthority().equals(role)) {
                return true;
            }
        }
        return false;
    }

    public static String getRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().get().getAuthority();
    }
}