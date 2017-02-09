package org.views;

import org.helpers.ClientOrgBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.pojos.Clients;
import org.pojos.MaritalStatus;
import org.pojos.Orgs;
import org.utils.ClientsUtils;
import org.utils.MaritalUtils;
import org.helpers.Message;
import org.utils.OrgsUtils;
import org.helpers.UserSession;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class MaritalView {

    private MaritalStatus marital_status;
    private MaritalStatus update_marital_status;
    private List<MaritalStatus> marital_statuses;

    private int activeIndex = 0;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    @ManagedProperty(value = "#{clientOrgBean}")
    private ClientOrgBean clientOrgBean;

    // edit
    public void edit(MaritalStatus marital) {
        this.update_marital_status = MaritalUtils.get(marital.getId());

        Clients client = update_marital_status.getClients();
        Orgs org = update_marital_status.getOrgs();

        clientOrgBean.setClient_id(client != null ? client.getId() : 0);
        clientOrgBean.setOrg_id(org != null ? org.getId() : 0);

        activeIndex = 2;
    }

    // insert & update
    public void save(MaritalStatus marital) {

        if (marital.getMaritalTitle().trim().length() > 0) {

            Clients client = (clientOrgBean.getClient_id() == 0 ? userSession.getUser().getClients() : ClientsUtils.get(clientOrgBean.getClient_id()));
            Orgs org = (clientOrgBean.getOrg_id() == 0 ? userSession.getOrg() : OrgsUtils.get(clientOrgBean.getOrg_id()));

            marital.setClients(client);
            marital.setOrgs(org);

            boolean added = MaritalUtils.save(marital);

            if (added) {
                Message.addMessage(marital.getMaritalTitle() + " status saved Successfully", "INFO");
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Marital Status Title is Required", "WARN");
        }
    }

    // delete
    public void delete(MaritalStatus marital) {

        boolean deleted = MaritalUtils.delete(marital);

        if (deleted) {
            Message.addMessage(marital.getMaritalTitle() + " status Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }

    @PostConstruct
    private void init() {
        this.marital_status = new MaritalStatus();
        this.update_marital_status = new MaritalStatus();
    }

    public MaritalStatus getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(MaritalStatus marital_status) {
        this.marital_status = marital_status;
    }

    public MaritalStatus getUpdate_marital_status() {
        return update_marital_status;
    }

    public void setUpdate_marital_status(MaritalStatus update_marital_status) {
        this.update_marital_status = update_marital_status;
    }

    public List<MaritalStatus> getMarital_statuses() {
        marital_statuses = new ArrayList<>();
        marital_statuses = MaritalUtils.list(userSession.getUser(), userSession.getOrg());
        return marital_statuses;
    }

    public void setMarital_statuses(List<MaritalStatus> marital_statuses) {
        this.marital_statuses = marital_statuses;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public ClientOrgBean getClientOrgBean() {
        return clientOrgBean;
    }

    public void setClientOrgBean(ClientOrgBean clientOrgBean) {
        this.clientOrgBean = clientOrgBean;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

}
