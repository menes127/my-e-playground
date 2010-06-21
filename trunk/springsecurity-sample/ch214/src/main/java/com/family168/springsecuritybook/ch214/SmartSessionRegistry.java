package com.family168.springsecuritybook.ch214;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import org.springframework.security.concurrent.SessionInformation;
import org.springframework.security.concurrent.SessionRegistry;
import org.springframework.security.concurrent.SessionRegistryImpl;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.session.HttpSessionDestroyedEvent;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;


public class SmartSessionRegistry extends SessionRegistryImpl {
    public synchronized void registerNewSession(String sessionId,
        Object principal) {
        //
        // convert for SmartPrincipal
        //
        if (!(principal instanceof SmartPrincipal)) {
            principal = new SmartPrincipal(SecurityContextHolder.getContext()
                                                                .getAuthentication());
        }

        super.registerNewSession(sessionId, principal);
    }
}
