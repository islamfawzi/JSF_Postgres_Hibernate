package org.views;

import org.helpers.ClientOrgBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.pojos.Clients;
import org.pojos.Employees;
import org.pojos.Orgs;
import org.primefaces.model.DualListModel;

import org.utils.ClientsUtils;
import org.utils.EmployeesUtils;
import org.helpers.Message;
import org.utils.OrgsUtils;
import org.helpers.UserSession;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class EmployeesView {

    private Employees employee;
    private Employees update_employee;
    private List<Employees> employees;
 
    private int activeIndex = 0;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    @ManagedProperty(value = "#{clientOrgBean}")
    private ClientOrgBean clientOrgBean;

    public void edit(Employees employee) {

        this.update_employee = EmployeesUtils.get(employee.getId());

        Clients client = update_employee.getClients();
        Orgs org = update_employee.getOrgs();

        clientOrgBean.setClient_id(client != null ? client.getId() : 0);
        clientOrgBean.setOrg_id(org != null ? org.getId() : 0);

        activeIndex = 2;
    }

    public void save(Employees employee) {

        if (employee.getEmpFullname().trim().length() > 0) {
            
            Clients client = (clientOrgBean.getClient_id() == 0 ? userSession.getUser().getClients() : ClientsUtils.get(clientOrgBean.getClient_id()));
            Orgs org = (clientOrgBean.getOrg_id() == 0 ? userSession.getOrg() : OrgsUtils.get(clientOrgBean.getOrg_id()));

            employee.setClients(client);
            employee.setOrgs(org);

            boolean added = EmployeesUtils.save(employee);

            if (added) {
                Message.addMessage(employee.getEmpFullname() + " saved Successfully", "INFO");
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Employee Name is Required", "WARN");
        }
    }

    public void delete(Employees employee) {

        boolean deleted = EmployeesUtils.delete(employee);

        if (deleted) {
            Message.addMessage(employee.getEmpFullname() + " Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }
    
    @PostConstruct
    private void init() {
        this.employee = new Employees();
        this.update_employee = new Employees();
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public Employees getUpdate_employee() {
        return update_employee;
    }

    public void setUpdate_employee(Employees update_employee) {
        this.update_employee = update_employee;
    }

    public List<Employees> getEmployees() {
        employees = new ArrayList<Employees>();
        employees = EmployeesUtils.list(userSession.getUser(), userSession.getOrg());
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
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
