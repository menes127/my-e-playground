package com.family168.springsecuritybook.ch201;

import org.springframework.security.annotation.Secured;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;


public class MessageServiceImpl implements MessageService {
    public String adminMessage() {
        return "admin message";
    }

    public String adminDate() {
        return "admin " + System.currentTimeMillis();
    }

    //@RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    //@DenyAll
    //@PermitAll
    @Secured({"ROLE_ADMIN",
        "ROLE_USER"
    })
    public String userMessage() {
        return "user message";
    }

    public String userDate() {
        return "user " + System.currentTimeMillis();
    }
}
