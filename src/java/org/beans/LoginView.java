/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.pojos.Users;
import org.utils.LoginUtils;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class LoginView {

   private Users user;

    public void addUser(){
    
        LoginUtils.save(user);
    }
    
    @PostConstruct
    private void init(){
        user = new Users();
    }
    
    
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }  
    
}
