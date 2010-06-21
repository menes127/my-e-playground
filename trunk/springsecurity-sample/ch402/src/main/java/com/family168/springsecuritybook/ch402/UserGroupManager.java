package com.family168.springsecuritybook.ch402;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.GroupManager;

import java.util.ArrayList;
import java.util.List;


public class UserGroupManager {
    private GroupManager groupManager;

    public void setGroupManager(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    public List<UserGroupBean> getAll() {
        List<UserGroupBean> list = new ArrayList<UserGroupBean>();
        String[] groups = groupManager.findAllGroups();

        for (String groupName : groups) {
            String[] members = groupManager.findUsersInGroup(groupName);
            GrantedAuthority[] authorities = groupManager.findGroupAuthorities(groupName);
            UserGroupBean bean = new UserGroupBean();

            bean.setName(groupName);
            bean.setMembers(members);
            bean.setAuthorities(authorities);
            list.add(bean);
        }

        return list;
    }

    public UserGroupBean get(String groupName) {
        String[] members = groupManager.findUsersInGroup(groupName);
        GrantedAuthority[] authorities = groupManager.findGroupAuthorities(groupName);
        UserGroupBean bean = new UserGroupBean();

        bean.setName(groupName);
        bean.setMembers(members);
        bean.setAuthorities(authorities);

        return bean;
    }

    public void save(String groupName, String[] authorities) {
        GrantedAuthority[] gas = new GrantedAuthority[authorities.length];

        for (int i = 0; i < authorities.length; i++) {
            gas[i] = new GrantedAuthorityImpl(authorities[i].trim());
        }

        groupManager.createGroup(groupName, gas);
    }

    public void remove(String groupName) {
        groupManager.deleteGroup(groupName);
    }

    public void update(String oldName, String groupName, String[] members,
        String[] authorities) {
        String[] usernames = groupManager.findUsersInGroup(oldName);

        for (String username : usernames) {
            groupManager.removeUserFromGroup(username, oldName);
        }

        for (String member : members) {
            if ((member != null) && !member.equals("")) {
                groupManager.addUserToGroup(member, oldName);
            }
        }

        GrantedAuthority[] gas = groupManager.findGroupAuthorities(groupName);

        for (GrantedAuthority ga : gas) {
            groupManager.removeGroupAuthority(oldName, ga);
        }

        for (int i = 0; i < authorities.length; i++) {
            String auth = authorities[i];

            if ((auth != null) && !auth.trim().equals("")) {
                GrantedAuthority ga = new GrantedAuthorityImpl(auth.trim());
                groupManager.addGroupAuthority(oldName, ga);
            }
        }

        groupManager.renameGroup(oldName, groupName);
    }
}
