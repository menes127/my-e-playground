package com.family168.springsecuritybook.ch213;

import org.springframework.context.annotation.Scope;

import org.springframework.security.BadCredentialsException;
import org.springframework.security.ui.AbstractProcessingFilter;

import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


@Component
@Scope("request")
public class LoginBean {
    private String username = "";
    private String password = "";
    private boolean rememberMe = false;
    private boolean loggedIn = false;

    public String doLogin() throws IOException, ServletException {
        ExternalContext context = FacesContext.getCurrentInstance()
                                              .getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher(
                "/j_spring_security_check");

        dispatcher.forward((ServletRequest) context.getRequest(),
            (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();

        // It's OK to return null here because Faces is just going to exit.
        return null;
    }

    @PostConstruct
    @SuppressWarnings("unused")
    private void handleErrorMessage() {
        Exception e = (Exception) FacesContext.getCurrentInstance()
                                              .getExternalContext()
                                              .getSessionMap()
                                              .get(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);

        if (e instanceof BadCredentialsException) {
            FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap()
                        .put(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY,
                null);
            FacesContext.getCurrentInstance()
                        .addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username or password not valid.", null));
        }
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return this.rememberMe;
    }

    public void setRememberMe(final boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn(final boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
