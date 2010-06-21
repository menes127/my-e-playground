package com.family168.springsecuritybook.ch208;

import org.springframework.security.GrantedAuthority;


public class UserInfo extends BaseUserDetails {
    private static final long serialVersionUID = 1L;
    private String email;

    public UserInfo(String username, String password, boolean enabled,
        GrantedAuthority[] authorities) throws IllegalArgumentException {
        super(username, password, enabled, authorities);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(Object rhs) {
        if (!(rhs instanceof UserInfo) || (rhs == null)) {
            return false;
        }

        if (super.equals(rhs)) {
            UserInfo userInfo = (UserInfo) rhs;

            return ((this.email == null) && (userInfo.getEmail() == null)) ||
            ((this.email != null) && this.email.equals(userInfo.getEmail()));
        }

        return false;
    }

    public int hashCode() {
        int code = super.hashCode();

        if (this.email != null) {
            code *= -11;
        }

        return code;
    }
}
