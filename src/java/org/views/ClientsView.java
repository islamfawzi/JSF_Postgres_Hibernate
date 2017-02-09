package org.views;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import org.pojos.Clients;
import org.utils.ClientsUtils;
import org.helpers.Message;
import org.utils.OrgsUtils;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class ClientsView {

    private Clients client;
    private Clients update_client;
    private List<Clients> clients;

    private int activeIndex = 0;

    // set canEdit to true
    public void edit(Clients client) {

        this.update_client = ClientsUtils.get(client.getId());
        
        activeIndex = 2;
    }
    
    public String update(Clients client) {

        if (client.getClientName().trim().length() > 0) {

            boolean added = ClientsUtils.update(client);

            if (added) {
                Message.addMessage(client.getClientName() + " client Added Successfully", "INFO");

                return "clients.xhtml?faces-redirect=true";
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Client Title is Required", "WARN");
        }

        return null;
    }

    public String save(Clients client) {

        if (client.getClientName().trim().length() > 0) {

            boolean added = ClientsUtils.save(client);

            if (added) {
                Message.addMessage(client.getClientName() + " client Added Successfully", "INFO");

                return "clients.xhtml?faces-redirect=true";
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Client Title is Required", "WARN");
        }

        return null;
    }

    public void delete(Clients client) {

        boolean deleted = ClientsUtils.delete(client);

        if (deleted) {
            Message.addMessage(client.getClientName() + " client Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }

    @PostConstruct
    private void init() {
        this.client = new Clients();
        this.update_client = new Clients();
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Clients getUpdate_client() {
        return update_client;
    }

    public void setUpdate_client(Clients update_client) {
        this.update_client = update_client;
    }
    
    public List<Clients> getClients() {
        clients = new ArrayList<>();
        clients = ClientsUtils.list(false);
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
