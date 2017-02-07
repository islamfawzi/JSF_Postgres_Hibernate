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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MaritalStatus generated by hbm2java
 */
@Entity
@Table(name = "marital_status", schema = "public"
)
public class MaritalStatus implements java.io.Serializable {

    private int id;
    private Clients clients;
    private Orgs orgs;
    private String maritalTitle;
    private boolean maritalStatus = true;
    private Set employeeses = new HashSet(0);

    private boolean canEdit = false;

    public MaritalStatus() {
    }

    public MaritalStatus(int id, String maritalTitle, boolean maritalStatus) {
        this.id = id;
        this.maritalTitle = maritalTitle;
        this.maritalStatus = maritalStatus;
    }

    public MaritalStatus(int id, Clients clients, Orgs orgs, String maritalTitle, boolean maritalStatus, Set employeeses) {
        this.id = id;
        this.clients = clients;
        this.orgs = orgs;
        this.maritalTitle = maritalTitle;
        this.maritalStatus = maritalStatus;
        this.employeeses = employeeses;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    public Clients getClients() {
        return this.clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    public Orgs getOrgs() {
        return this.orgs;
    }

    public void setOrgs(Orgs orgs) {
        this.orgs = orgs;
    }

    @Column(name = "marital_title", nullable = false)
    public String getMaritalTitle() {
        return this.maritalTitle;
    }

    public void setMaritalTitle(String maritalTitle) {
        this.maritalTitle = maritalTitle;
    }

    @Column(name = "marital_status", nullable = false)
    public boolean isMaritalStatus() {
        return this.maritalStatus;
    }

    public void setMaritalStatus(boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "maritalStatus")
    public Set getEmployeeses() {
        return this.employeeses;
    }

    public void setEmployeeses(Set employeeses) {
        this.employeeses = employeeses;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

}
