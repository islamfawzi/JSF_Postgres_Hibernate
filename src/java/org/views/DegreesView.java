package org.views;

import org.helpers.ClientOrgBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.pojos.Clients;
import org.pojos.Degrees;
import org.pojos.Orgs;
import org.utils.ClientsUtils;
import org.utils.DegreesUtils;
import org.helpers.Message;
import org.utils.OrgsUtils;
import org.helpers.UserSession;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class DegreesView {

    private Degrees degree;
    private Degrees update_degree;
    private List<Degrees> degrees;

    private int activeIndex = 0;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    @ManagedProperty(value = "#{clientOrgBean}")
    private ClientOrgBean clientOrgBean;

    public void edit(Degrees degree) {
        this.update_degree = DegreesUtils.get(degree.getId());

        Clients client = update_degree.getClients();
        Orgs org = update_degree.getOrgs();

        clientOrgBean.setClient_id(client != null ? client.getId() : 0);
        clientOrgBean.setOrg_id(org != null ? org.getId() : 0);

        activeIndex = 2;
    }

    public void save(Degrees degree) {

        if (degree.getDegreeTitle().trim().length() > 0) {

            Clients client = (clientOrgBean.getClient_id() == 0 ? userSession.getUser().getClients() : ClientsUtils.get(clientOrgBean.getClient_id()));
            Orgs org = (clientOrgBean.getOrg_id() == 0 ? userSession.getOrg() : OrgsUtils.get(clientOrgBean.getOrg_id()));

            degree.setClients(client);
            degree.setOrgs(org);

            boolean added = DegreesUtils.save(degree);

            if (added) {
                Message.addMessage(degree.getDegreeTitle() + " degree saved Successfully", "INFO");
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Degree Title is Required", "WARN");
        }
    }

    public void delete(Degrees degree) {

        boolean deleted = DegreesUtils.delete(degree);

        if (deleted) {
            Message.addMessage(degree.getDegreeTitle() + " degree Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }

    @PostConstruct
    private void init() {
        this.degree = new Degrees();
        this.update_degree = new Degrees();
    }

    public Degrees getDegree() {
        return degree;
    }

    public void setDegree(Degrees degree) {
        this.degree = degree;
    }

    public Degrees getUpdate_degree() {
        return update_degree;
    }

    public void setUpdate_degree(Degrees update_degree) {
        this.update_degree = update_degree;
    }

    public List<Degrees> getDegrees() {
        degrees = new ArrayList<>();
        degrees = DegreesUtils.list(userSession.getUser(), userSession.getOrg());
        return degrees;
    }

    public void setDegrees(List<Degrees> degrees) {
        this.degrees = degrees;
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
