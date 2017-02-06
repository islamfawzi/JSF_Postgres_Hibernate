package org.views;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
public class OrgsView {

    private Orgs org;
    private Orgs update_org;
    
    private List<Orgs> orgs;
    private List<Clients> clients;

    private int activeIndex = 0;

    // set canEdit to true
    public void edit(Orgs org) {
        
        this.update_org = OrgsUtils.get(org.getId());
        this.update_org.setClient_id(org.getClients().getId());
        
        activeIndex = 2;
    }

    public String save(Orgs org) {

        if (org.getOrg_name().trim().length() > 0) {

            /* get Client by id  
             * set it to Client Object of Org */
            org.setClients(ClientsUtils.get(org.getClient_id()));

            // save Org object into DB
            boolean added = OrgsUtils.save(org);

            if (added) {
                Message.addMessage(org.getOrg_name() + " organization Added Successfully", "INFO");

                return "orgs.xhtml?faces-redirect=true";
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Organization Title is Required", "WARN");
        }

        return null;
    }

    public void delete(Orgs org) {

        boolean deleted = OrgsUtils.delete(org);

        if (deleted) {
            Message.addMessage(org.getOrg_name() + " organization Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }

    @PostConstruct
    private void init() {
        this.org = new Orgs();
        this.update_org = new Orgs();
    }

    public Orgs getOrg() {
        return org;
    }

    public void setOrg(Orgs org) {
        this.org = org;
    }

    public Orgs getUpdate_org() {
        return update_org;
    }

    public void setUpdate_org(Orgs update_org) {
        this.update_org = update_org;
    }
    
    public List<Orgs> getOrgs() {
        orgs = new ArrayList<>();
        orgs = OrgsUtils.list();
        return orgs;
    }

    public void setOrgs(List<Orgs> orgs) {
        this.orgs = orgs;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public List<Clients> getClients() {
        clients = new ArrayList<>();
        clients = ClientsUtils.list(true);
        return clients;
    }

    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }

}
