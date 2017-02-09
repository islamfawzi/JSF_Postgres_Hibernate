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
 * Positions generated by hbm2java
 */
@Entity
@Table(name = "positions", schema = "public"
)
public class Positions implements java.io.Serializable {

    private int id;
    private Clients clients;
    private Orgs orgs;
    private String posTitle;
    private boolean posStatus = true;
    private Set employeeses = new HashSet(0);

    public Positions() {
    }

    public Positions(int id, String posTitle, boolean posStatus) {
        this.id = id;
        this.posTitle = posTitle;
        this.posStatus = posStatus;
    }

    public Positions(int id, Clients clients, Orgs orgs, String posTitle, boolean posStatus, Set employeeses) {
        this.id = id;
        this.clients = clients;
        this.orgs = orgs;
        this.posTitle = posTitle;
        this.posStatus = posStatus;
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

    @Column(name = "pos_title", nullable = false)
    public String getPosTitle() {
        return this.posTitle;
    }

    public void setPosTitle(String posTitle) {
        this.posTitle = posTitle;
    }

    @Column(name = "pos_status", nullable = false)
    public boolean isPosStatus() {
        return this.posStatus;
    }

    public void setPosStatus(boolean posStatus) {
        this.posStatus = posStatus;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "positions")
    public Set getEmployeeses() {
        return this.employeeses;
    }

    public void setEmployeeses(Set employeeses) {
        this.employeeses = employeeses;
    }

    @Override
    public String toString() {
        return "Positions{" + "id=" + id + ", posTitle=" + posTitle + '}';
    }

}
