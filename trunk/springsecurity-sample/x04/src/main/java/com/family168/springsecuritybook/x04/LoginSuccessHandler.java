package com.family168.springsecuritybook.x04;

import org.springframework.beans.factory.InitializingBean;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationProcessingFilter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginSuccessHandler
    extends SavedRequestAwareAuthenticationSuccessHandler
    implements InitializingBean {
    private UsernamePasswordAuthenticationProcessingFilter usernamePasswordAuthenticationProcessingFilter;

    public void onAuthenticationSuccess(HttpServletRequest request,
        HttpServletResponse response, Authentication authentication)
        throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        if (userDetails.getUsername().equals("user")) {
            session.setAttribute("email", "user@163.com");
        } else if (userDetails.getUsername().equals("admin")) {
            session.setAttribute("email", "admin@163.com");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }

    public void afterPropertiesSet() {
        usernamePasswordAuthenticationProcessingFilter.setAuthenticationSuccessHandler(this);
    }

    public void setUsernamePasswordAuthenticationProcessingFilter(
        UsernamePasswordAuthenticationProcessingFilter usernamePasswordAuthenticationProcessingFilter) {
        this.usernamePasswordAuthenticationProcessingFilter = usernamePasswordAuthenticationProcessingFilter;
    }
}
