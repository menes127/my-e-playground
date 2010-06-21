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


public class AccountService {
    private List<Account> list = new ArrayList<Account>();
    private MutableAclService mutableAclService;

    public void setMutableAclService(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    @Secured({"ROLE_USER",
        "AFTER_ACL_READ"
    })
    public Account get(Long id) {
        for (Account account : list) {
            if (account.getId().equals(id)) {
                return account;
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
    public void save(String accountContent, String owner) {
        Account account = new Account();
        account.setId(System.currentTimeMillis());
        account.setContent(accountContent);
        account.setOwner(owner);
        account.setCreateDate(new Date());
        account.setUpdateDate(new Date());

        this.save(account);
    }

    @Transactional
    @Secured({"ROLE_USER",
        "AFTER_ACL_CREATE"
    })
    public void save(Account account) {
        list.add(account);
    }

    public void update(Long id, String accountContent) {
        Account account = this.get(id);
        account.setContent(accountContent);
        account.setUpdateDate(new Date());
    }

    @Transactional
    @Secured({"ACL_DELETE",
        "AFTER_ACL_DELETE"
    })
    @AclDomainAware(Account.class)
    public void removeById(Long id) {
        Account account = this.get(id);
        list.remove(account);
    }
}
