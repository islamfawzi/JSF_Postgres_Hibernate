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

    private List<Positions> positions;
    
    private int activeIndex = 0;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;
    
    @ManagedProperty(value = "#{clientOrgBean}")
    private ClientOrgBean clientOrgBean;

    public void edit(Positions pos) {

        this.update_position = PositionsUtils.get(pos.getId());
        
        Clients client = update_position.getClients();
        Orgs org = update_position.getOrgs();
        
        clientOrgBean.setClient_id(client != null? client.getId() : 0);
        clientOrgBean.setOrg_id(org != null? org.getId() : 0);
        
        activeIndex = 2;
    }

    
    public void save(Positions position) {

        if (position.getPosTitle().trim().length() > 0) {

            Clients client = (clientOrgBean.getClient_id() == 0? userSession.getUser().getClients() : ClientsUtils.get(clientOrgBean.getClient_id()));
            Orgs org = (clientOrgBean.getOrg_id() == 0? userSession.getOrg()  : OrgsUtils.get(clientOrgBean.getOrg_id()));
            
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

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }
    
    public ClientOrgBean getClientOrgBean() {
        return clientOrgBean;
    }

    public void setClientOrgBean(ClientOrgBean clientOrgBean) {
        this.clientOrgBean = clientOrgBean;
    }
}
