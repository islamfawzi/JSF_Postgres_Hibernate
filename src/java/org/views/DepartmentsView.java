package org.views;

import org.helpers.ClientOrgBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.pojos.Clients;
import org.pojos.Departments;
import org.pojos.Orgs;
import org.utils.ClientsUtils;
import org.utils.DepartmentsUtils;
import org.helpers.Message;
import org.utils.OrgsUtils;
import org.helpers.UserSession;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class DepartmentsView {

    private Departments department;
    private Departments update_department;
    private List<Departments> departments;

    private int activeIndex = 0;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    @ManagedProperty(value = "#{clientOrgBean}")
    private ClientOrgBean clientOrgBean;

    public void edit(Departments dept) {

        this.update_department = DepartmentsUtils.get(dept.getId());
    
        Clients client = update_department.getClients();
        Orgs org = update_department.getOrgs();
        
        clientOrgBean.setClient_id(client != null? client.getId() : 0);
        clientOrgBean.setOrg_id(org != null? org.getId() : 0);

        activeIndex = 2;
    }

    
    public void save(Departments department) {

        if (department.getDeptTitle().trim().length() > 0) {

            Clients client = (clientOrgBean.getClient_id() == 0 ? userSession.getUser().getClients() : ClientsUtils.get(clientOrgBean.getClient_id()));
            Orgs org = (clientOrgBean.getOrg_id() == 0 ? userSession.getOrg() : OrgsUtils.get(clientOrgBean.getOrg_id()));

            department.setClients(client);
            department.setOrgs(org);

            boolean added = DepartmentsUtils.save(department);

            if (added) {
                Message.addMessage(department.getDeptTitle() + " Department Saved Successfully", "INFO");
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Department Title is Required", "WARN");
        }
    }

    public void delete(Departments dept) {

        boolean deleted = DepartmentsUtils.delete(dept);

        if (deleted) {
            Message.addMessage(dept.getDeptTitle() + " Department Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }

    @PostConstruct
    private void init() {
        this.department = new Departments();
        this.update_department = new Departments();
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public Departments getUpdate_department() {
        return update_department;
    }

    public void setUpdate_department(Departments update_department) {
        this.update_department = update_department;
    }

    public List<Departments> getDepartments() {
        departments = new ArrayList<Departments>();
        departments = DepartmentsUtils.list(userSession.getUser(), userSession.getOrg());
        return departments;
    }

    public void setDepartments(List<Departments> departments) {
        this.departments = departments;
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
