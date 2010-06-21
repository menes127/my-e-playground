package com.family168.springsecuritybook.ch301;

import org.springframework.security.acls.MutableAcl;
import org.springframework.security.acls.MutableAclService;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.objectidentity.ObjectIdentityImpl;
import org.springframework.security.acls.sid.GrantedAuthoritySid;
import org.springframework.security.acls.sid.PrincipalSid;
import org.springframework.security.annotation.Secured;

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

    @Secured({"ROLE_USER",
        "AFTER_ACL_READ"
    })
    public Message get(Long id) {
        for (Message message : list) {
            if (message.getId().equals(id)) {
                return message;
            }
        }

        return null;
    }

    @Secured({"ROLE_USER",
        "AFTER_ACL_COLLECTION_READ"
    })
    public List getAll() {
        return list;
    }

    @Transactional
    @Secured("ROLE_USER")
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

    public void update(Long id, String messageContent) {
        Message message = this.get(id);
        message.setMessage(messageContent);
        message.setUpdateDate(new Date());
    }

    public void removeById(Long id) {
        Message message = this.get(id);
        this.remove(message);
    }

    @Transactional
    @Secured("ACL_MESSAGE_DELETE")
    public void remove(Message message) {
        list.remove(message);

        ObjectIdentity oid = new ObjectIdentityImpl(Message.class,
                message.getId());
        mutableAclService.deleteAcl(oid, false);
    }
}
