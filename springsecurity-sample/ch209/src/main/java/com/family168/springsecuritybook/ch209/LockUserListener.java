package com.family168.springsecuritybook.ch209;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import org.springframework.security.Authentication;
import org.springframework.security.event.authentication.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.event.authentication.AuthenticationSuccessEvent;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;


public class LockUserListener implements ApplicationListener {
    private ServletContext servletContext;
    private UserInfoService userInfoService;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            AuthenticationFailureBadCredentialsEvent authEvent = (AuthenticationFailureBadCredentialsEvent) event;
            Authentication authentication = (Authentication) authEvent.getSource();
            String username = (String) authentication.getPrincipal();
            addCount(username);
        }

        if (event instanceof AuthenticationSuccessEvent) {
            AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent) event;
            Authentication authentication = (Authentication) authEvent.getSource();
            UserInfo userInfo = (UserInfo) authentication.getPrincipal();
            String username = userInfo.getUsername();
            cleanCount(username);
        }
    }

    protected void addCount(String username) {
        Map<String, Integer> lockUserMap = getLockUserMap();
        Integer count = lockUserMap.get(username);

        if (count == null) {
            lockUserMap.put(username, Integer.valueOf(1));
        } else {
            int resultCount = count.intValue() + 1;

            if (resultCount > 3) {
                UserInfo userInfo = (UserInfo) userInfoService.loadUserByUsername(username);
                userInfo.lockAccount();
            } else {
                lockUserMap.put(username, Integer.valueOf(resultCount));
            }
        }
    }

    protected void cleanCount(String username) {
        Map<String, Integer> lockUserMap = getLockUserMap();

        if (lockUserMap.containsKey(username)) {
            lockUserMap.remove(username);
        }
    }

    protected Map<String, Integer> getLockUserMap() {
        Map<String, Integer> lockUserMap = (Map<String, Integer>) servletContext.getAttribute(
                "LOCK_USER_MAP");

        if (lockUserMap == null) {
            lockUserMap = new HashMap<String, Integer>();
            servletContext.setAttribute("LOCK_USER_MAP", lockUserMap);
        }

        return lockUserMap;
    }
}
