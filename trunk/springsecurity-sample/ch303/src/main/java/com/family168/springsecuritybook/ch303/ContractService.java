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

        this.save(contract);
    }

    @Transactional
    @Secured({"ROLE_USER",
        "AFTER_ACL_CREATE"
    })
    public void save(Contract contract) {
        list.add(contract);
    }

    public void update(Long id, String contractContent) {
        Contract contract = this.get(id);
        contract.setContent(contractContent);
        contract.setUpdateDate(new Date());
    }

    @Transactional
    @Secured({"ACL_DELETE",
        "AFTER_ACL_DELETE"
    })
    @AclDomainAware(Contract.class)
    public void removeById(Long id) {
        Contract contract = this.get(id);
        list.remove(contract);
    }
}
