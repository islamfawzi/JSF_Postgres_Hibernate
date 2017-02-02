
package org.views;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import org.pojos.Clients;
import org.pojos.Orgs;
import org.utils.ClientsUtils;
import org.utils.Message;
import org.utils.OrgsUtils;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class ClientsView {


    private Clients client;
    private List<Clients> clients;
    private List<Orgs> all_orgs;
    
    private int activeIndex = 0;
    
    // set canEdit to true
    public void edit(Clients client) {
        
        this.client.setId(client.getId());
        this.client.setClient_name(client.getClient_name());
        this.client.setClient_desc(client.getClient_desc());
        this.client.setClient_status(client.getClient_status());
        this.client.setCanEdit(true);
        
        
        activeIndex = 1;
    }
    
    public String inlineEdit(Clients client){
    
        client.setCanEdit(true);
        System.out.println("can Edit: "+ client);
        
        return null;
    }

    // update Client
    public String update(Clients client) {
        
        boolean updated = ClientsUtils.update(client);
        
        if (updated) {
        
            Message.addMessage(client.getClient_name() + " client Updated Successfully", "INFO");
            
            client.setCanEdit(false);    
            
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }

        
       return null;
    }
    
    
    public String save() {
    
        System.out.println("Client : " + client.toString());
        if (this.client.getClient_name().trim().length() > 0) {

            boolean added = ClientsUtils.save(this.client);

            if (added) {
                Message.addMessage(this.client.getClient_name()+ " client Added Successfully", "INFO");
                
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
            Message.addMessage(client.getClient_name()+ " client Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }
    
    
    @PostConstruct
    private void init(){
        this.client = new Clients();
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public List<Clients> getClients() {
        clients = new ArrayList<>();
        clients = ClientsUtils.list();
        return clients;
    }

    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }

    public List<Orgs> getAll_orgs() {
        all_orgs = new ArrayList<Orgs>();
        all_orgs = OrgsUtils.list();
        return all_orgs;
    }

    public void setAll_orgs(List<Orgs> all_orgs) {
        this.all_orgs = all_orgs;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }
    
    
}
