package org.helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.pojos.Clients;
import org.pojos.Orgs;
import org.utils.ClientsUtils;
import org.utils.OrgsUtils;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class ClientOrgBean implements Serializable{

    private List<Clients> clients;
    private List<Orgs> orgs;

    private int client_id = 0;
    private int org_id = 0;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

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

            if (client_id != 0) {
                orgs = OrgsUtils.getOrgsPerClient(ClientsUtils.get(client_id));
            } else {
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

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }
    
    public void ajax(){}

}
