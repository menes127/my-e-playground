package com.family168.springsecuritybook.ch303.afterinvocation;

import com.family168.springsecuritybook.ch303.handler.AclHandler;
import com.family168.springsecuritybook.ch303.voter.AclDomainAware;

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

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Iterator;
import java.util.List;


public class DeleteAclEntryAfterInvocationProvider
    extends AbstractAclEntryAfterInvocationProvider {
    private static Log logger = LogFactory.getLog(DeleteAclEntryAfterInvocationProvider.class);

    public DeleteAclEntryAfterInvocationProvider(AclService aclService,
        Permission[] requirePermission) {
        super(aclService, "AFTER_ACL_DELETE", requirePermission);
    }

    public Object decide(Authentication authentication, Object object,
        ConfigAttributeDefinition config, Object returnedObject)
        throws AccessDeniedException {
        Iterator iter = config.getConfigAttributes().iterator();

        while (iter.hasNext()) {
            ConfigAttribute attr = (ConfigAttribute) iter.next();

            if (this.supports(attr)) {
                Object domainObject = getDomainObjectInstance(object,
                        processDomainObjectClass);

                Iterator cit = this.handlers.iterator();

                while (cit.hasNext()) {
                    AclHandler handler = (AclHandler) cit.next();

                    if (handler.supports(domainObject, returnedObject)) {
                        handler.delete(authentication, domainObject, config,
                            returnedObject);

                        break;
                    }
                }
            }
        }

        return returnedObject;
    }

    public Object getDomainObjectInstance(Object secureObject,
        Class processDomainObjectClass) {
        MethodInvocation invocation = (MethodInvocation) secureObject;

        Method method = invocation.getMethod();
        Class[] params = method.getParameterTypes();

        for (int i = 0; i < params.length; i++) {
            if (processDomainObjectClass.isAssignableFrom(params[i])) {
                return invocation.getArguments()[i];
            }
        }

        Serializable id = null;

        for (int i = 0; i < params.length; i++) {
            if (Serializable.class.isAssignableFrom(params[i])) {
                id = (Serializable) invocation.getArguments()[i];

                break;
            }
        }

        if (id == null) {
            throw new AuthorizationServiceException("MethodInvocation: " +
                invocation + " did not provide any ID argument.");
        }

        if (method.isAnnotationPresent(AclDomainAware.class)) {
            try {
                Class domainClass = method.getAnnotation(AclDomainAware.class)
                                          .value();
                Object instance = domainClass.newInstance();
                Method setter = domainClass.getDeclaredMethod("setId",
                        new Class[] { id.getClass() });
                setter.invoke(instance, new Object[] { id });

                return instance;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        throw new AuthorizationServiceException("MethodInvocation: " +
            invocation + " did not provide any argument of type: " +
            processDomainObjectClass);
    }
}
