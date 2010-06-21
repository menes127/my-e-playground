package com.family168.springsecuritybook.ch302;

import org.springframework.context.ApplicationContext;

import org.springframework.security.acls.AccessControlEntry;
import org.springframework.security.acls.Acl;
import org.springframework.security.acls.AclService;
import org.springframework.security.acls.MutableAcl;
import org.springframework.security.acls.MutableAclService;
import org.springframework.security.acls.NotFoundException;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.objectidentity.ObjectIdentity;
import org.springframework.security.acls.objectidentity.ObjectIdentityImpl;
import org.springframework.security.acls.sid.PrincipalSid;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class PermissionService {
    private MutableAclService mutableAclService;

    public void setMutableAclService(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    @Transactional
    public void addPermission(long id, String clz, String recipient, int mask) {
        PrincipalSid sid = new PrincipalSid(recipient);
        Permission permission = BasePermission.buildFromMask(mask);

        ObjectIdentity oid = null;

        if (clz.equals("account")) {
            oid = new ObjectIdentityImpl(Account.class, id);
        } else if (clz.equals("contract")) {
            oid = new ObjectIdentityImpl(Contract.class, id);
        } else if (clz.equals("message")) {
            oid = new ObjectIdentityImpl(Message.class, id);
        }

        MutableAcl acl;

        try {
            acl = (MutableAcl) mutableAclService.readAclById(oid);
        } catch (NotFoundException nfe) {
            acl = mutableAclService.createAcl(oid);
        }

        acl.insertAce(acl.getEntries().length, permission, sid, true);
        mutableAclService.updateAcl(acl);
    }

    @Transactional
    public void deletePermission(long id, String clz, String recipient, int mask) {
        PrincipalSid sid = new PrincipalSid(recipient);
        Permission permission = BasePermission.buildFromMask(mask);

        ObjectIdentity oid = null;

        if (clz.equals("account")) {
            oid = new ObjectIdentityImpl(Account.class, id);
        } else if (clz.equals("contract")) {
            oid = new ObjectIdentityImpl(Contract.class, id);
        } else if (clz.equals("message")) {
            oid = new ObjectIdentityImpl(Message.class, id);
        }

        MutableAcl acl = (MutableAcl) mutableAclService.readAclById(oid);
        AccessControlEntry[] entries = acl.getEntries();

        for (int i = 0; i < entries.length; i++) {
            if (entries[i].getSid().equals(sid) &&
                    entries[i].getPermission().equals(permission)) {
                acl.deleteAce(i);
            }
        }

        mutableAclService.updateAcl(acl);
    }
}
