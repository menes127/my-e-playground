package com.family168.springsecuritybook.ch303.afterinvocation;

import com.family168.springsecuritybook.ch303.handler.AclHandler;

import org.aopalliance.intercept.MethodInvocation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.MessageSourceAccessor;

import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.AuthorizationServiceException;
import org.springframework.security.ConfigAttribute;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.SpringSecurityMessageSource;
import org.springframework.security.SpringSecurityMessageSource;
import org.springframework.security.acls.AclService;
import org.springframework.security.acls.AclService;
import org.springframework.security.acls.Permission;
import org.springframework.security.acls.Permission;
import org.springframework.security.afterinvocation.AbstractAclProvider;

import java.lang.reflect.Method;

import java.util.Iterator;
import java.util.List;


public abstract class AbstractAclEntryAfterInvocationProvider
    extends AbstractAclProvider implements MessageSourceAware {
    private static Log logger = LogFactory.getLog(AbstractAclEntryAfterInvocationProvider.class);
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    protected List handlers;

    public AbstractAclEntryAfterInvocationProvider(AclService aclService,
        String processConfigAttribute, Permission[] requirePermission) {
        super(aclService, processConfigAttribute, requirePermission);
    }

    public abstract Object decide(Authentication authentication, Object object,
        ConfigAttributeDefinition config, Object returnedObject)
        throws AccessDeniedException;

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    public List getHandlers() {
        return handlers;
    }

    public void setHandlers(List newList) {
        checkIfValidList(newList);

        Iterator iter = newList.iterator();

        while (iter.hasNext()) {
            Object currentObject = null;

            try {
                currentObject = iter.next();

                AclHandler attemptToCast = (AclHandler) currentObject;
            } catch (ClassCastException cce) {
                throw new IllegalArgumentException("AclCreator " +
                    currentObject.getClass().getName() +
                    " must implement AclCreator");
            }
        }

        this.handlers = newList;
    }

    private void checkIfValidList(List listToCheck) {
        if ((listToCheck == null) || (listToCheck.size() == 0)) {
            throw new IllegalArgumentException(
                "A list of AfterInvocationProviders is required");
        }
    }

    public abstract Object getDomainObjectInstance(Object secureObject,
        Class processDomainObjectClass);
}
