package com.family168.springsecuritybook.ch213;

import org.springframework.security.BadCredentialsException;
import org.springframework.security.ui.AbstractProcessingFilter;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


public class LoginErrorPhaseListener implements PhaseListener {
    private static final long serialVersionUID = -1216620620302322995L;

    public void beforePhase(final PhaseEvent arg0) {
        Exception e = (Exception) FacesContext.getCurrentInstance()
                                              .getExternalContext()
                                              .getSessionMap()
                                              .get(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);

        if (e instanceof BadCredentialsException) {
            FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap()
                        .put(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY,
                null);
            FacesContext.getCurrentInstance()
                        .addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username or password not valid.", null));
        }
    }

    public void afterPhase(final PhaseEvent arg0) {
    }

    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}
