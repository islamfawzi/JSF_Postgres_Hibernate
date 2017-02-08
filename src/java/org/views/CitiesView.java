package org.views;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.pojos.Cities;
import org.pojos.Clients;
import org.pojos.Orgs;
import org.utils.CitiesUtils;
import org.utils.ClientsUtils;
import org.utils.Message;
import org.utils.OrgsUtils;
import org.utils.UserSession;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class CitiesView {

    private Cities city;
    private Cities update_city;
    private List<Cities> cities;

    private int activeIndex = 0;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    @ManagedProperty(value = "#{clientOrgBean}")
    private ClientOrgBean clientOrgBean;

    // set canEdit to true
    public void edit(Cities city) {

        this.update_city = CitiesUtils.get(city.getId());

        Clients client = update_city.getClients();
        Orgs org = update_city.getOrgs();

        clientOrgBean.setClient_id(client != null ? client.getId() : 0);
        clientOrgBean.setOrg_id(org != null ? org.getId() : 0);

        activeIndex = 2;
    }

    // insert new city
    public void save(Cities city) {

        if (city.getCityTitle().trim().length() > 0) {

            Clients client = (clientOrgBean.getClient_id() == 0 ? userSession.getUser().getClients() : ClientsUtils.get(clientOrgBean.getClient_id()));
            Orgs org = (clientOrgBean.getOrg_id() == 0 ? userSession.getOrg() : OrgsUtils.get(clientOrgBean.getOrg_id()));

            city.setClients(client);
            city.setOrgs(org);

            boolean added = CitiesUtils.save(city);

            if (added) {
                Message.addMessage(city.getCityTitle() + " City saved Successfully", "INFO");
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("City Title is Required", "WARN");
        }
    }

    // delete City
    public void delete(Cities city) {

        boolean deleted = CitiesUtils.delete(city);

        if (deleted) {
            Message.addMessage(city.getCityTitle() + " City Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }

    @PostConstruct
    private void init() {
        this.city = new Cities();
        this.update_city = new Cities();
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public Cities getUpdate_city() {
        return update_city;
    }

    public void setUpdate_city(Cities update_city) {
        this.update_city = update_city;
    }

    public List<Cities> getCities() {
        cities = new ArrayList<Cities>();
        cities = CitiesUtils.list(userSession.getUser(), userSession.getOrg());
        return cities;
    }

    public void setCities(List<Cities> cities) {
        this.cities = cities;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
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

}
