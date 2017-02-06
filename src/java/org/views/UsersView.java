package org.views;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.pojos.Clients;
import org.pojos.Users;
import org.utils.ClientsUtils;
import org.utils.Message;
import org.utils.UsersUtils;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class UsersView {

    private Users user;
    private Users update_user;

    private List<Users> users;
    private List<Clients> clients;

    private int activeIndex = 0;

    // set canEdit to true
    public void edit(Users user) {

        this.update_user = UsersUtils.get(user.getId());
        this.update_user.setClient_id(user.getClient().getId());

        activeIndex = 2;
    }
    
    public String save(Users user) {

        if (user.getUsername().trim().length() > 0) {

            /* get Client by id  
             * set it to Client Object of User */
            user.setClient(ClientsUtils.get(user.getClient_id()));

            // save Org object into DB
            boolean added = UsersUtils.save(user);

            if (added) {
                Message.addMessage(user.getUsername() + " user Added Successfully", "INFO");

                return "users.xhtml?faces-redirect=true";
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            activeIndex = 2;
            Message.addMessage("Username is Required", "WARN");
        }

        return null;
    }
    
    public void delete(Users user) {

        boolean deleted = UsersUtils.delete(user);

        if (deleted) {
            Message.addMessage(user.getUsername() + " user Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }

    @PostConstruct
    private void init() {
        this.user = new Users();
        this.update_user = new Users();
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(Users update_user) {
        this.update_user = update_user;
    }

    public List<Users> getUsers() {
        users = new ArrayList<>();
        users = UsersUtils.list(false);
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Clients> getClients() {
        clients = new ArrayList<>();
        clients = ClientsUtils.list(true);
        return clients;
    }

    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }
    
    
}
