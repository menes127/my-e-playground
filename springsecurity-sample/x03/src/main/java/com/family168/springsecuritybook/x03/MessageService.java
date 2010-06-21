package com.family168.springsecuritybook.x03;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MessageService {
    private List<Message> list = new ArrayList<Message>();
    private MutableAclService mutableAclService;

    public void setMutableAclService(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostAuthorize("hasPermission(returnObject, 'read') or hasPermission(returnObject, 'admin')")
    public Message get(Long id) {
        for (Message message : list) {
            if (message.getId().equals(id)) {
                return message;
            }
        }

        return null;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostFilter("hasPermission(filterObject, 'read') or hasPermission(filterObject, 'admin')")
    public List getAll() {
        return list;
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    public void save(String messageContent, String owner) {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMessage(messageContent);
        message.setOwner(owner);
        message.setCreateDate(new Date());
        message.setUpdateDate(new Date());
        list.add(message);

        ObjectIdentity oid = new ObjectIdentityImpl(Message.class,
                message.getId());
        MutableAcl acl = mutableAclService.createAcl(oid);
        acl.insertAce(0, BasePermission.ADMINISTRATION,
            new PrincipalSid(owner), true);
        acl.insertAce(1, BasePermission.DELETE,
            new GrantedAuthoritySid("ROLE_ADMIN"), true);
        acl.insertAce(2, BasePermission.READ,
            new GrantedAuthoritySid("ROLE_USER"), true);
        mutableAclService.updateAcl(acl);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public void update(Long id, String messageContent) {
        Message message = this.get(id);
        modifyMessage(message, messageContent);
    }

    @PreAuthorize("#message.owner == principal.name")
    public void modifyMessage(Message message, String messageContent) {
        message.setMessage(messageContent);
        message.setUpdateDate(new Date());
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_USER')")
    public void remove(Long id) {
        Message message = this.get(id);
        deleteMessage(message);
    }

    @PreAuthorize("hasPermission(#message, 'admin')")
    public void deleteMessage(Message message) {
        list.remove(message);

        ObjectIdentity oid = new ObjectIdentityImpl(Message.class,
                message.getId());
        mutableAclService.deleteAcl(oid, false);
    }
}
