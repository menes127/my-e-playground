package com.family168.springsecuritybook.ch209;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

import org.springframework.util.Assert;

import java.util.SortedSet;
import java.util.TreeSet;


public class BaseUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    protected String password;
    protected String username;
    protected GrantedAuthority[] authorities;
    protected boolean accountNonExpired;
    protected boolean accountNonLocked;
    protected boolean credentialsNonExpired;
    protected boolean enabled;

    public BaseUserDetails(String username, String password, boolean enabled,
        GrantedAuthority[] authorities) throws IllegalArgumentException {
        this(username, password, enabled, true, true, authorities);
    }

    public BaseUserDetails(String username, String password, boolean enabled,
        boolean accountNonExpired, boolean credentialsNonExpired,
        GrantedAuthority[] authorities) throws IllegalArgumentException {
        this(username, password, enabled, accountNonExpired,
            credentialsNonExpired, true, authorities);
    }

    public BaseUserDetails(String username, String password, boolean enabled,
        boolean accountNonExpired, boolean credentialsNonExpired,
        boolean accountNonLocked, GrantedAuthority[] authorities)
        throws IllegalArgumentException {
        if (((username == null) || "".equals(username)) || (password == null)) {
            throw new IllegalArgumentException(
                "Cannot pass null or empty values to constructor");
        }

        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        setAuthorities(authorities);
    }

    public boolean equals(Object rhs) {
        if (!(rhs instanceof BaseUserDetails) || (rhs == null)) {
            return false;
        }

        BaseUserDetails user = (BaseUserDetails) rhs;

        // We rely on constructor to guarantee any User has non-null and >0
        // authorities
        if (user.getAuthorities().length != this.getAuthorities().length) {
            return false;
        }

        for (int i = 0; i < this.getAuthorities().length; i++) {
            if (!this.getAuthorities()[i].equals(user.getAuthorities()[i])) {
                return false;
            }
        }

        // We rely on constructor to guarantee non-null username and password
        return (this.getPassword().equals(user.getPassword()) &&
        this.getUsername().equals(user.getUsername()) &&
        (this.isAccountNonExpired() == user.isAccountNonExpired()) &&
        (this.isAccountNonLocked() == user.isAccountNonLocked()) &&
        (this.isCredentialsNonExpired() == user.isCredentialsNonExpired()) &&
        (this.isEnabled() == user.isEnabled()));
    }

    public GrantedAuthority[] getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int hashCode() {
        int code = 9792;

        if (this.getAuthorities() != null) {
            for (int i = 0; i < this.getAuthorities().length; i++) {
                code = code * (this.getAuthorities()[i].hashCode() % 7);
            }
        }

        if (this.getPassword() != null) {
            code = code * (this.getPassword().hashCode() % 7);
        }

        if (this.getUsername() != null) {
            code = code * (this.getUsername().hashCode() % 7);
        }

        if (this.isAccountNonExpired()) {
            code = code * -2;
        }

        if (this.isAccountNonLocked()) {
            code = code * -3;
        }

        if (this.isCredentialsNonExpired()) {
            code = code * -5;
        }

        if (this.isEnabled()) {
            code = code * -7;
        }

        return code;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void expireAccount() {
        this.accountNonExpired = false;
    }

    public void lockAccount() {
        this.accountNonLocked = false;
    }

    public void expireCredential() {
        this.credentialsNonExpired = false;
    }

    public void disable() {
        this.enabled = false;
    }

    protected void setAuthorities(GrantedAuthority[] authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority array");

        // Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-xxx)
        SortedSet sorter = new TreeSet();

        for (int i = 0; i < authorities.length; i++) {
            Assert.notNull(authorities[i],
                "Granted authority element " + i +
                " is null - GrantedAuthority[] cannot contain any null elements");
            sorter.add(authorities[i]);
        }

        this.authorities = (GrantedAuthority[]) sorter.toArray(new GrantedAuthority[sorter.size()]);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toString()).append(": ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired)
          .append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired)
          .append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked)
          .append("; ");

        if (this.getAuthorities() != null) {
            sb.append("Granted Authorities: ");

            for (int i = 0; i < this.getAuthorities().length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }

                sb.append(this.getAuthorities()[i].toString());
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }
}
