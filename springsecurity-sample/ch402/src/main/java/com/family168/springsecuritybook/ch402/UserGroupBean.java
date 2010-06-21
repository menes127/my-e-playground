package com.family168.springsecuritybook.ch402;

import org.springframework.security.GrantedAuthority;


public class UserGroupBean {
    private String name;
    private String[] members;
    private GrantedAuthority[] authorities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public GrantedAuthority[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(GrantedAuthority[] authorities) {
        this.authorities = authorities;
    }

    public String getMember() {
        if (members == null) {
            return "";
        }

        StringBuffer buff = new StringBuffer();

        for (String mem : members) {
            buff.append(mem).append(",");
        }

        if (members.length != 0) {
            buff.deleteCharAt(buff.length() - 1);
        }

        return buff.toString();
    }

    public String getAuthority() {
        if (authorities == null) {
            return "";
        }

        StringBuffer buff = new StringBuffer();

        for (GrantedAuthority auth : authorities) {
            buff.append(auth.getAuthority()).append(",");
        }

        if (authorities.length != 0) {
            buff.deleteCharAt(buff.length() - 1);
        }

        return buff.toString();
    }
}
