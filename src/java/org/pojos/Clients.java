package org.pojos;
// Generated Feb 6, 2017 5:18:07 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clients generated by hbm2java
 */
@Entity
@Table(name = "clients", schema = "public"
)
public class Clients implements java.io.Serializable {

    private int id;
    private String clientName;
    private boolean clientStatus = true;
    private String clientDesc;
    private Set citieses = new HashSet(0);
    private Set employeeses = new HashSet(0);
    private Set payrollTemplateses = new HashSet(0);
    private Set actionsTranses = new HashSet(0);
    private Set payrollProcesseses = new HashSet(0);
    private Set payrollFormulases = new HashSet(0);
    private Set payrollEmployeeItemses = new HashSet(0);
    private Set orgses = new HashSet(0);
    private Set userses = new HashSet(0);
    private Set payrollItemses = new HashSet(0);
    private Set periodses = new HashSet(0);
    private Set actionsesForClientId = new HashSet(0);
    private Set calenders = new HashSet(0);
    private Set degreeses = new HashSet(0);
    private Set payrollItemsCatses = new HashSet(0);
    private Set actionTypeses = new HashSet(0);
    private Set departmentses = new HashSet(0);
    private Set militaryStatuses = new HashSet(0);
    private Set actionsesForOrgId = new HashSet(0);
    private Set maritalStatuses = new HashSet(0);
    private Set positionses = new HashSet(0);

    private boolean canEdit = false;

    public Clients() {
    }

    public Clients(int id) {
        this.id = id;
    }

    public Clients(int id, String clientName, boolean clientStatus, String clientDesc, Set citieses, Set employeeses, Set payrollTemplateses, Set actionsTranses, Set payrollProcesseses, Set payrollFormulases, Set payrollEmployeeItemses, Set orgses, Set userses, Set payrollItemses, Set periodses, Set actionsesForClientId, Set calenders, Set degreeses, Set payrollItemsCatses, Set actionTypeses, Set departmentses, Set militaryStatuses, Set actionsesForOrgId, Set maritalStatuses, Set positionses) {
        this.id = id;
        this.clientName = clientName;
        this.clientStatus = clientStatus;
        this.clientDesc = clientDesc;
        this.citieses = citieses;
        this.employeeses = employeeses;
        this.payrollTemplateses = payrollTemplateses;
        this.actionsTranses = actionsTranses;
        this.payrollProcesseses = payrollProcesseses;
        this.payrollFormulases = payrollFormulases;
        this.payrollEmployeeItemses = payrollEmployeeItemses;
        this.orgses = orgses;
        this.userses = userses;
        this.payrollItemses = payrollItemses;
        this.periodses = periodses;
        this.actionsesForClientId = actionsesForClientId;
        this.calenders = calenders;
        this.degreeses = degreeses;
        this.payrollItemsCatses = payrollItemsCatses;
        this.actionTypeses = actionTypeses;
        this.departmentses = departmentses;
        this.militaryStatuses = militaryStatuses;
        this.actionsesForOrgId = actionsesForOrgId;
        this.maritalStatuses = maritalStatuses;
        this.positionses = positionses;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "client_name")
    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Column(name = "client_status", nullable = false)
    public boolean isClientStatus() {
        return this.clientStatus;
    }

    public void setClientStatus(boolean clientStatus) {
        this.clientStatus = clientStatus;
    }

    @Column(name = "client_desc")
    public String getClientDesc() {
        return this.clientDesc;
    }

    public void setClientDesc(String clientDesc) {
        this.clientDesc = clientDesc;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getCitieses() {
        return this.citieses;
    }

    public void setCitieses(Set citieses) {
        this.citieses = citieses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getEmployeeses() {
        return this.employeeses;
    }

    public void setEmployeeses(Set employeeses) {
        this.employeeses = employeeses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getPayrollTemplateses() {
        return this.payrollTemplateses;
    }

    public void setPayrollTemplateses(Set payrollTemplateses) {
        this.payrollTemplateses = payrollTemplateses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getActionsTranses() {
        return this.actionsTranses;
    }

    public void setActionsTranses(Set actionsTranses) {
        this.actionsTranses = actionsTranses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getPayrollProcesseses() {
        return this.payrollProcesseses;
    }

    public void setPayrollProcesseses(Set payrollProcesseses) {
        this.payrollProcesseses = payrollProcesseses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getPayrollFormulases() {
        return this.payrollFormulases;
    }

    public void setPayrollFormulases(Set payrollFormulases) {
        this.payrollFormulases = payrollFormulases;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getPayrollEmployeeItemses() {
        return this.payrollEmployeeItemses;
    }

    public void setPayrollEmployeeItemses(Set payrollEmployeeItemses) {
        this.payrollEmployeeItemses = payrollEmployeeItemses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getOrgses() {
        return this.orgses;
    }

    public void setOrgses(Set orgses) {
        this.orgses = orgses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getUserses() {
        return this.userses;
    }

    public void setUserses(Set userses) {
        this.userses = userses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getPayrollItemses() {
        return this.payrollItemses;
    }

    public void setPayrollItemses(Set payrollItemses) {
        this.payrollItemses = payrollItemses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getPeriodses() {
        return this.periodses;
    }

    public void setPeriodses(Set periodses) {
        this.periodses = periodses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clientsByClientId")
    public Set getActionsesForClientId() {
        return this.actionsesForClientId;
    }

    public void setActionsesForClientId(Set actionsesForClientId) {
        this.actionsesForClientId = actionsesForClientId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getCalenders() {
        return this.calenders;
    }

    public void setCalenders(Set calenders) {
        this.calenders = calenders;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getDegreeses() {
        return this.degreeses;
    }

    public void setDegreeses(Set degreeses) {
        this.degreeses = degreeses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getPayrollItemsCatses() {
        return this.payrollItemsCatses;
    }

    public void setPayrollItemsCatses(Set payrollItemsCatses) {
        this.payrollItemsCatses = payrollItemsCatses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getActionTypeses() {
        return this.actionTypeses;
    }

    public void setActionTypeses(Set actionTypeses) {
        this.actionTypeses = actionTypeses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getDepartmentses() {
        return this.departmentses;
    }

    public void setDepartmentses(Set departmentses) {
        this.departmentses = departmentses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getMilitaryStatuses() {
        return this.militaryStatuses;
    }

    public void setMilitaryStatuses(Set militaryStatuses) {
        this.militaryStatuses = militaryStatuses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clientsByOrgId")
    public Set getActionsesForOrgId() {
        return this.actionsesForOrgId;
    }

    public void setActionsesForOrgId(Set actionsesForOrgId) {
        this.actionsesForOrgId = actionsesForOrgId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getMaritalStatuses() {
        return this.maritalStatuses;
    }

    public void setMaritalStatuses(Set maritalStatuses) {
        this.maritalStatuses = maritalStatuses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    public Set getPositionses() {
        return this.positionses;
    }

    public void setPositionses(Set positionses) {
        this.positionses = positionses;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    @Override
    public String toString() {
        return "Clients{" + "id=" + id + ", clientName=" + clientName + '}';
    }

}
