package com.user.org.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthorizationHeaderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        AuthorizationHeaderHolder
                .getAuthorizationHeader().setAuthorizationHeader(httpServletRequest.getHeader(AuthorizationHeader.AUTHORIZATION_HEADER));
        filterChain.doFilter(httpServletRequest, servletResponse);

    }
}
