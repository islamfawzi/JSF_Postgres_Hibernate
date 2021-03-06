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
 * Cities generated by hbm2java
 */
@Entity
@Table(name = "cities", schema = "public"
)
public class Cities implements java.io.Serializable {

    private int id;
    private Clients clients;
    private Orgs orgs;
    private String cityTitle;
    private boolean cityStatus = true;
    private Set employeeses = new HashSet(0);

    public Cities() {
    }

    public Cities(int id, String cityTitle, boolean cityStatus) {
        this.id = id;
        this.cityTitle = cityTitle;
        this.cityStatus = cityStatus;
    }

    public Cities(int id, Clients clients, Orgs orgs, String cityTitle, boolean cityStatus, Set employeeses) {
        this.id = id;
        this.clients = clients;
        this.orgs = orgs;
        this.cityTitle = cityTitle;
        this.cityStatus = cityStatus;
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

    @Column(name = "city_title", nullable = false)
    public String getCityTitle() {
        return this.cityTitle;
    }

    public void setCityTitle(String cityTitle) {
        this.cityTitle = cityTitle;
    }

    @Column(name = "city_status", nullable = false)
    public boolean isCityStatus() {
        return this.cityStatus;
    }

    public void setCityStatus(boolean cityStatus) {
        this.cityStatus = cityStatus;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cities")
    public Set getEmployeeses() {
        return this.employeeses;
    }

    public void setEmployeeses(Set employeeses) {
        this.employeeses = employeeses;
    }

}
