package org.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.pojos.Clients;
import org.pojos.Orgs;
import org.pojos.Positions;
import org.utils.ClientsUtils;
import org.utils.Message;
import org.utils.OrgsUtils;
import org.utils.PositionsUtils;
import org.utils.UserSession;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class PositionsView {

    private Positions position;
    private Positions update_position;

    private int client_id = 0;
    private int org_id = 0;

    private List<Positions> positions;
    private List<Clients> clients;
    private List<Orgs> orgs;

    private int activeIndex = 0;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    // set canEdit to true
    public void edit(Positions pos) {

        this.update_position = PositionsUtils.get(pos.getId());
        activeIndex = 2;
    }

    // update Position
    public void update(Positions pos) {

        boolean updated = PositionsUtils.update(pos);

        if (updated) {
            Message.addMessage(pos.getPosTitle() + " Position Updated Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }

        pos.setCanEdit(false);
    }

    public void save(Positions position) {

        if (position.getPosTitle().trim().length() > 0) {

            Clients client = (client_id == 0? userSession.getUser().getClients() : ClientsUtils.get(client_id));
            Orgs org = (org_id == 0? userSession.getOrg()  : OrgsUtils.get(org_id));
            
            System.out.println(">>>>>>>>>>>>>>>>> " + client);
            System.out.println(">>>>>>>>>>>>>>>>> " + org);
            
            position.setClients(client);
            position.setOrgs(org);
            
            boolean added = PositionsUtils.save(position);

            if (added) {
                Message.addMessage(position.getPosTitle() + " Position Saved Successfully", "INFO");
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Position Title is Required", "WARN");
        }
    }

    public void delete(Positions pos) {

        boolean deleted = PositionsUtils.delete(pos);

        if (deleted) {
            Message.addMessage(pos.getPosTitle() + " Position Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }
    
    @PostConstruct
    private void init() {
        this.position = new Positions();
        this.update_position = new Positions();
    }

    public List<Positions> getPositions() {

        positions = new ArrayList<Positions>();
        positions = PositionsUtils.list(userSession.getUser(), userSession.getOrg());
        return positions;
    }

    public void setPositions(List<Positions> positions) {
        this.positions = positions;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public Positions getUpdate_position() {
        return update_position;
    }

    public void setUpdate_position(Positions update_position) {
        this.update_position = update_position;
    }

    public List<Clients> getClients() {

        if (userSession.getUser().getId() == 0) {
            clients = ClientsUtils.list(true);
        } else {
            clients = new ArrayList<>(Arrays.asList(userSession.getUser().getClients()));
        }

        return clients;
    }

    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }

    public List<Orgs> getOrgs() {

        if (userSession.getUser().getId() == 0) {
            
            if(client_id != 0){
                orgs = OrgsUtils.getOrgsPerClient(ClientsUtils.get(client_id));
            }else{
                orgs = OrgsUtils.list(true);
            }
            
        } else if (userSession.getOrg() == null) {
            orgs = OrgsUtils.getOrgsPerClient(userSession.getUser().getClients());
        } else {
            orgs = new ArrayList<>(Arrays.asList(userSession.getOrg()));
        }
        
        return orgs;
    }
    
    
    public void setOrgs(List<Orgs> orgs) {
        this.orgs = orgs;
    }

    public int getClient_id() {
        if (userSession.getUser().getId() != 0) {
            client_id = userSession.getUser().getClients().getId();
        }

        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getOrg_id() {
        if (userSession.getUser().getId() != 0
         && userSession.getOrg() != null) {
            
            org_id = userSession.getOrg().getId();
        }
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }
    
    public void ajax(){}
    
}
