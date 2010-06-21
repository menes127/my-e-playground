package com.family168.springsecuritybook.ch401;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * User Manager.
 *
 * @author Lingo xyz20003@gmail.com
 * www.family168.com
 */
public class UserManager {
    private JdbcTemplate jdbcTemplate;
    private UserDetailsManager userDetailsManager;
    private PasswordEncoder passwordEncoder;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setUserDetailsManager(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * get all of user.
     */
    public List<UserBean> getAll() {
        String sql = "select username,password,enabled,authority" +
            " from users u left join authorities a on u.username=a.username";
        List<Map> list = jdbcTemplate.queryForList(sql);

        List<UserBean> userList = new ArrayList<UserBean>();
        UserBean ub = null;

        for (Map map : list) {
            if (ub == null) {
                ub = new UserBean((String) map.get("username"),
                        (String) map.get("password"),
                        (Boolean) map.get("enabled"));
                ub.addAuthority((String) map.get("authority"));
            } else if (ub.getUsername().equals(map.get("username"))) {
                ub.addAuthority((String) map.get("authority"));
            } else {
                userList.add(ub);
                //
                ub = new UserBean((String) map.get("username"),
                        (String) map.get("password"),
                        (Boolean) map.get("enabled"));
                ub.addAuthority((String) map.get("authority"));
            }
        }

        if (!list.isEmpty()) {
            userList.add(ub);
        }

        return userList;
    }

    /**
     * get user by username.
     */
    public UserBean get(String username) {
        String sql = "select username,password,enabled,authority" +
            " from users u left join authorities a on u.username=a.username" +
            " where u.username=?";
        List<Map> list = jdbcTemplate.queryForList(sql,
                new Object[] { username });

        UserBean ub = null;

        for (Map map : list) {
            if (ub == null) {
                ub = new UserBean((String) map.get("username"),
                        (String) map.get("password"),
                        (Boolean) map.get("enabled"));
                ub.addAuthority((String) map.get("authority"));
            } else if (ub.getUsername().equals(map.get("username"))) {
                ub.addAuthority((String) map.get("authority"));
            }
        }

        return ub;
    }

    /**
     * check whether username exists.
     */
    public boolean userExists(String username) {
        return userDetailsManager.userExists(username);
    }

    /**
     * create a new user and insert he to database.
     */
    public void save(String username, String password, boolean enabled,
        String[] authorities) {
        GrantedAuthority[] gas = new GrantedAuthority[authorities.length];

        for (int i = 0; i < authorities.length; i++) {
            gas[i] = new GrantedAuthorityImpl(authorities[i].trim());
        }

        String encodedPassword = passwordEncoder.encodePassword(password,
                username);
        UserDetails ud = new User(username, encodedPassword, enabled, true,
                true, true, gas);
        userDetailsManager.createUser(ud);
    }

    /**
     * update a user information, includes username, enabled or authorities.
     */
    public void update(String username, boolean enabled, String[] authorities) {
        GrantedAuthority[] gas = new GrantedAuthority[authorities.length];

        for (int i = 0; i < authorities.length; i++) {
            gas[i] = new GrantedAuthorityImpl(authorities[i].trim());
        }

        UserDetails oldUserDetails = userDetailsManager.loadUserByUsername(username);
        UserDetails ud = new User(username, oldUserDetails.getPassword(),
                enabled, oldUserDetails.isAccountNonExpired(),
                oldUserDetails.isAccountNonLocked(),
                oldUserDetails.isCredentialsNonExpired(), gas);
        userDetailsManager.updateUser(ud);
    }

    /**
     * remove a user by username.
     */
    public void remove(String username) {
        userDetailsManager.deleteUser(username);
    }

    /**
     * let current user change password.
     */
    public void changePassword(String oldPassword, String newPassword) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                                                                     .getAuthentication()
                                                                     .getPrincipal();
        String username = userDetails.getUsername();

        String encodedOldPassword = passwordEncoder.encodePassword(oldPassword,
                username);
        String encodedNewPassword = passwordEncoder.encodePassword(newPassword,
                username);
        userDetailsManager.changePassword(encodedOldPassword, encodedNewPassword);
    }
}
