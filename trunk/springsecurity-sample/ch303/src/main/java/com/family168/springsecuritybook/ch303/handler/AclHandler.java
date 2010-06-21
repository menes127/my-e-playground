package com.family168.springsecuritybook.ch303.handler;

import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttribute;
import org.springframework.security.ConfigAttributeDefinition;


public interface AclHandler {
    void create(Authentication authentication, Object object,
        ConfigAttributeDefinition config, Object returnedObject)
        throws AccessDeniedException;

    void delete(Authentication authentication, Object object,
        ConfigAttributeDefinition config, Object returnedObject)
        throws AccessDeniedException;

    boolean supports(Object domainObject, Object returnedObject);
}
