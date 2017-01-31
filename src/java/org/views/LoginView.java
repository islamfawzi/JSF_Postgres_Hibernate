/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.views;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.pojos.Users;
import org.utils.UsersUtils;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class LoginView {

    private Users user;

    public String login() {

        boolean success = UsersUtils.login(user.getUsername(), user.getPassword());
        
        // if logging success set the User in the session
        if (success) {
            setLoginSession(user);
            return "/index?faces-redirect=true";
        }

        return "login";
    }
    
    public String logout(){
    
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    private static void setLoginSession(Users user) {
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("loggedUser", user);
    }

    @PostConstruct
    private void init() {
        user = new Users();
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
