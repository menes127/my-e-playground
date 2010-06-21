package com.family168.springsecuritybook.ch303.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttribute;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.acls.*;
import org.springframework.security.acls.MutableAcl;
import org.springframework.security.acls.MutableAclService;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.objectidentity.ObjectIdentityImpl;
import org.springframework.security.acls.objectidentity.ObjectIdentityImpl;
import org.springframework.security.acls.sid.GrantedAuthoritySid;
import org.springframework.security.acls.sid.PrincipalSid;
import org.springframework.security.acls.sid.PrincipalSid;
import org.springframework.security.acls.sid.Sid;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import org.springframework.transaction.annotation.Transactional;


public class DefaultAclHandler implements AclHandler {
    private MutableAclService mutableAclService;

    public void setMutableAclService(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    @Transactional
    public void create(Authentication authentication, Object object,
        ConfigAttributeDefinition config, Object returnedObject)
        throws AccessDeniedException {
        ObjectIdentity oid = new ObjectIdentityImpl(object);
        MutableAcl acl = mutableAclService.createAcl(oid);
        acl.insertAce(0, BasePermission.ADMINISTRATION,
            new PrincipalSid(getUsername()), true);
    }

    @Transactional
    public void delete(Authentication authentication, Object object,
        ConfigAttributeDefinition config, Object returnedObject)
        throws AccessDeniedException {
        ObjectIdentity oid = new ObjectIdentityImpl(object);
        mutableAclService.deleteAcl(oid, false);
    }

    public boolean supports(Object domainObject, Object returnedObject) {
        return true;
    }

    protected String getUsername() {
        Authentication auth = SecurityContextHolder.getContext()
                                                   .getAuthentication();

        if (auth.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) auth.getPrincipal()).getUsername();
        } else {
            return auth.getPrincipal().toString();
        }
    }
}
