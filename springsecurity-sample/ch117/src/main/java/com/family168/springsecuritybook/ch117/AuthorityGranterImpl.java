package com.family168.springsecuritybook.ch117;

import org.springframework.security.providers.jaas.AuthorityGranter;

import java.security.Principal;

import java.util.HashSet;
import java.util.Set;


public class AuthorityGranterImpl implements AuthorityGranter {
    public Set grant(Principal principal) {
        Set rtnSet = new HashSet();

        if (principal.getName().equals("TEST_PRINCIPAL")) {
            rtnSet.add("ROLE_USER");
            rtnSet.add("ROLE_ADMIN");
        }

        return rtnSet;
    }
}
