
package org.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;
import org.pojos.Users;

/**
 *
 * @author islam
 */
public class AuthListener implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent event) {
        
        FacesContext context = event.getFacesContext();
        
        String location = context.getViewRoot().getViewId();
        
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        
        Users user = (Users) session.getAttribute("loggedUser");
        
        if(user == null && !location.endsWith("login.xhtml")){
        
            NavigationHandler navigationHandler  = context.getApplication().getNavigationHandler();
            navigationHandler.handleNavigation(context, null, "login?faces-redirect=true");
        }
        
        
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
