package com.family168.springsecuritybook.ch214;

import org.springframework.security.Authentication;
import org.springframework.security.ui.WebAuthenticationDetails;
import org.springframework.security.userdetails.UserDetails;

import org.springframework.util.Assert;


public class SmartPrincipal {
    private String username;
    private String ip;

    public SmartPrincipal(String username, String ip) {
        Assert.notNull(username,
            "username cannot be null (violation of interface contract)");
        Assert.notNull(ip,
            "username cannot be null (violation of interface contract)");
        this.username = username;
        this.ip = ip;
    }

    public SmartPrincipal(Authentication authentication) {
        Assert.notNull(authentication,
            "authentication cannot be null (violation of interface contract)");

        String username = null;

        if (authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            username = (String) authentication.getPrincipal();
        }

        String ip = ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();
        this.username = username;
        this.ip = ip;
    }

    public boolean equalsIp(SmartPrincipal smartPrincipal) {
        return this.ip.equals(smartPrincipal.ip);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SmartPrincipal) {
            SmartPrincipal smartPrincipal = (SmartPrincipal) obj;

            return username.equals(smartPrincipal.username);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "SmartPrincipal:{username=" + username + ",ip=" + ip + "}";
    }
}
