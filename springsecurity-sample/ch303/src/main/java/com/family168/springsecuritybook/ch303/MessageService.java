package com.family168.springsecuritybook.ch303;

import com.family168.springsecuritybook.ch303.voter.AclDomainAware;

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

        this.save(message);
    }

    @Transactional
    @Secured({"ROLE_USER",
        "AFTER_ACL_CREATE"
    })
    public void save(Message message) {
        list.add(message);
    }

    public void update(Long id, String messageContent) {
        Message message = this.get(id);
        message.setMessage(messageContent);
        message.setUpdateDate(new Date());
    }

    @Transactional
    @Secured({"ACL_DELETE",
        "AFTER_ACL_DELETE"
    })
    @AclDomainAware(Message.class)
    public void removeById(Long id) {
        Message message = this.get(id);
        list.remove(message);
    }
}
