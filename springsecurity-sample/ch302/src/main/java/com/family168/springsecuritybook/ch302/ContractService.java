package com.family168.springsecuritybook.ch302;

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


public class ContractService {
    private List<Contract> list = new ArrayList<Contract>();
    private MutableAclService mutableAclService;

    public void setMutableAclService(MutableAclService mutableAclService) {
        this.mutableAclService = mutableAclService;
    }

    @Secured({"ROLE_USER",
        "AFTER_ACL_READ"
    })
    public Contract get(Long id) {
        for (Contract contract : list) {
            if (contract.getId().equals(id)) {
                return contract;
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
    public void save(String contractContent, String owner) {
        Contract contract = new Contract();
        contract.setId(System.currentTimeMillis());
        contract.setContent(contractContent);
        contract.setOwner(owner);
        contract.setCreateDate(new Date());
        contract.setUpdateDate(new Date());
        list.add(contract);

        ObjectIdentity oid = new ObjectIdentityImpl(Contract.class,
                contract.getId());
        MutableAcl acl = mutableAclService.createAcl(oid);
        acl.insertAce(0, BasePermission.ADMINISTRATION,
            new PrincipalSid(owner), true);

        /*
                acl.insertAce(1, BasePermission.DELETE,
                    new GrantedAuthoritySid("ROLE_ADMIN"), true);
                acl.insertAce(2, BasePermission.READ,
                    new GrantedAuthoritySid("ROLE_USER"), true);
                mutableAclService.updateAcl(acl);
        */
    }

    public void update(Long id, String contractContent) {
        Contract contract = this.get(id);
        contract.setContent(contractContent);
        contract.setUpdateDate(new Date());
    }

    public void removeById(Long id) {
        Contract contract = this.get(id);
        this.remove(contract);
    }

    @Transactional
    @Secured("ACL_DELETE")
    public void remove(Contract contract) {
        list.remove(contract);

        ObjectIdentity oid = new ObjectIdentityImpl(Contract.class,
                contract.getId());
        mutableAclService.deleteAcl(oid, false);
    }
}
