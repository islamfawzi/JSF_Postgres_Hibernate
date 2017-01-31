
package org.utils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class Message {

    public static void addMessage(String details, String type) {
        
        FacesMessage message = null;
        switch(type){
            case "INFO":
                 message = new FacesMessage(FacesMessage.SEVERITY_INFO, details,  null);
                break;
              
            case "ERROR":
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, details,  null);
                break;
                
            case "WARN":
                message = new FacesMessage(FacesMessage.SEVERITY_WARN,  details,  null);
                break;
        }
        
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
