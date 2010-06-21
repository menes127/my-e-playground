package com.family168.springsecuritybook.ch211;

import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;

import javax.servlet.http.HttpServletRequest;


public class LoginFilter extends AuthenticationProcessingFilter {
    public Authentication attemptAuthentication(HttpServletRequest request)
        throws AuthenticationException {
        Authentication authentication = super.attemptAuthentication(request);

        String mark = request.getParameter("mark");
        request.getSession().setAttribute("mark", "mark");

        return authentication;
    }
}
