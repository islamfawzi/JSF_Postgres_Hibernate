/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.pojos.Orgs;
import org.pojos.Users;
import org.utils.OrgsUtils;
import org.helpers.UserSession;
import org.utils.UsersUtils;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class LoginView {

    private Users user;
    private int org_id;

    List<Orgs> orgs;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    public String login() {

        user = UsersUtils.login(user.getUsername(), user.getPassword());

        // if login success
        if (user != null) {

            userSession.setUser(user);

            // if System User
            if (user.getId() == 0) {
                return "/index?faces-redirect=true";
            } // if not System User
            else {
                return "choose-org";
            }
        } // if login did not success
        else {
            return "login?faces-redirect=true";
        }

    }

    public String submitOrg() {

        if (org_id > 0) {
            Orgs org = OrgsUtils.get(org_id);
            userSession.setOrg(org);
        }

        return "/index?faces-redirect=true";
    }

    public String logout() {

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

    public int getOrg_id() {
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }

    public List<Orgs> getOrgs() {
        Users user = userSession.getUser();
        if (user != null) {
            
            if(user.getId() == 0){
                // System
                orgs = OrgsUtils.list(true);
            }else{
                // Others
                orgs = OrgsUtils.getOrgsPerClient(user.getClients());
            }
        }
        return orgs;
    }

    public void setOrgs(List<Orgs> orgs) {
        this.orgs = orgs;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

}
