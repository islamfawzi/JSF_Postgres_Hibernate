package org.views;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.pojos.Clients;
import org.pojos.MilitaryStatus;
import org.pojos.Orgs;
import org.utils.ClientsUtils;
import org.utils.Message;
import org.utils.MilitaryUtils;
import org.utils.OrgsUtils;
import org.utils.UserSession;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class MilitaryView {

    private MilitaryStatus military_status;
    private MilitaryStatus update_military_status;
    private List<MilitaryStatus> military_statuses;

    private int activeIndex = 0;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    @ManagedProperty(value = "#{clientOrgBean}")
    private ClientOrgBean clientOrgBean;

    // set canEdit to true
    public void edit(MilitaryStatus military) {
        this.update_military_status = MilitaryUtils.get(military.getId());

        Clients client = update_military_status.getClients();
        Orgs org = update_military_status.getOrgs();

        clientOrgBean.setClient_id(client != null ? client.getId() : 0);
        clientOrgBean.setOrg_id(org != null ? org.getId() : 0);

        activeIndex = 2;
    }

    public void save(MilitaryStatus military) {

        if (military.getMilitaryTitle().trim().length() > 0) {

            Clients client = (clientOrgBean.getClient_id() == 0 ? userSession.getUser().getClients() : ClientsUtils.get(clientOrgBean.getClient_id()));
            Orgs org = (clientOrgBean.getOrg_id() == 0 ? userSession.getOrg() : OrgsUtils.get(clientOrgBean.getOrg_id()));

            military.setClients(client);
            military.setOrgs(org);

            boolean added = MilitaryUtils.save(military);

            if (added) {
                Message.addMessage(military.getMilitaryTitle() + " status saved Successfully", "INFO");
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Military Status Title is Required", "WARN");
        }
    }

    public void delete(MilitaryStatus military) {

        boolean deleted = MilitaryUtils.delete(military);

        if (deleted) {
            Message.addMessage(military.getMilitaryTitle() + " status Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }

    @PostConstruct
    private void init() {
        this.military_status = new MilitaryStatus();
        this.update_military_status = new MilitaryStatus();
    }

    public MilitaryStatus getMilitary_status() {
        return military_status;
    }

    public void setMilitary_status(MilitaryStatus military_status) {
        this.military_status = military_status;
    }

    public List<MilitaryStatus> getMilitary_statuses() {
        military_statuses = new ArrayList<>();
        military_statuses = MilitaryUtils.list(userSession.getUser(), userSession.getOrg());
        return military_statuses;
    }

    public void setMilitary_statuses(List<MilitaryStatus> military_statuses) {
        this.military_statuses = military_statuses;
    }

    public MilitaryStatus getUpdate_military_status() {
        return update_military_status;
    }

    public void setUpdate_military_status(MilitaryStatus update_military_status) {
        this.update_military_status = update_military_status;
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
