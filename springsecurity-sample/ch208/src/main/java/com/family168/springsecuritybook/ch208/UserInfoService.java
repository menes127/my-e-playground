package com.family168.springsecuritybook.ch208;

import org.springframework.dao.DataAccessException;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.providers.dao.DaoAuthenticationProvider;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;


public class UserInfoService implements UserDetailsService {
    private Map<String, UserInfo> userMap = null;

    public UserInfoService() {
        userMap = new HashMap<String, UserInfo>();

        UserInfo userInfo = null;
        userInfo = new UserInfo("user", "user", true,
                new GrantedAuthority[] { new GrantedAuthorityImpl("ROLE_USER") });
        userInfo.setEmail("user@family168.com");
        userMap.put("user", userInfo);
        userInfo = new UserInfo("admin", "admin", true,
                new GrantedAuthority[] {
                    new GrantedAuthorityImpl("ROLE_ADMIN"),
                    new GrantedAuthorityImpl("ROLE_USER")
                });
        userInfo.setEmail("admin@family168.com");
        userMap.put("admin", userInfo);
    }

    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException, DataAccessException {
        return userMap.get(username);
    }
}
