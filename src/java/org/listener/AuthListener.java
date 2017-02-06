package org.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;
import org.pojos.Users;
import org.utils.UserSession;

/**
 *
 * @author islam
 */
public class AuthListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {

        FacesContext context = event.getFacesContext();

        String location = context.getViewRoot().getViewId();
        NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        UserSession userSession
                = (UserSession) facesContext.getApplication()
                .createValueBinding("#{userSession}").getValue(facesContext);

        Users user = userSession.getUser();
        
        if (user == null && !location.endsWith("login.xhtml")) {

            navigationHandler.handleNavigation(context, null, "/login?faces-redirect=true");
        }
        
        if((location.endsWith("clients.xhtml") || 
           location.endsWith("orgs.xhtml") ||
           location.endsWith("users.xhtml") ) &&
            user.getId() != 0
          )
        {
        
            navigationHandler.handleNavigation(context, null, "/index?faces-redirect=true");
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
