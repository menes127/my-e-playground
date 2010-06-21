package com.family168.springsecuritybook.ch402;

import java.util.ArrayList;
import java.util.List;


public class UserBean {
    private String username;
    private String password;
    private Boolean enabled;
    private List<String> authorities = new ArrayList<String>();

    public UserBean(String username, String password, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authority) {
        this.authorities = authorities;
    }

    public void addAuthority(String authority) {
        if (authority != null) {
            authorities.add(authority);
        }
    }

    public String getAuthority() {
        StringBuffer buff = new StringBuffer();

        for (String auth : authorities) {
            buff.append(auth).append(",");
        }

        if (!authorities.isEmpty()) {
            buff.deleteCharAt(buff.length() - 1);
        }

        return buff.toString();
    }
}
