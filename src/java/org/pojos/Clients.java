package org.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author islam
 */
@Entity
@Table(name = "clients", schema = "public")
public class Clients implements java.io.Serializable {

    private int id;
    private String client_name;
    private String client_desc;
    private boolean client_status = true;
    
    private Set orgs = new HashSet(0);
    private Set userses = new HashSet(0);
    private Set positions = new HashSet(0);

    private boolean canEdit;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "client_name", nullable = false)
    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    @Column(name = "client_desc", nullable = false)
    public String getClient_desc() {
        return client_desc;
    }

    public void setClient_desc(String client_desc) {
        this.client_desc = client_desc;
    }

    @Column(name = "client_status")
    public boolean getClient_status() {
        return client_status;
    }

    public void setClient_status(boolean client_status) {
        this.client_status = client_status;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="clients")
    public Set getOrgs() {
        return orgs;
    }

    public void setOrgs(Set orgs) {
        this.orgs = orgs;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="clients")
    public Set getUserses() {
        return this.userses;
    }
    
    public void setUserses(Set userses) {
        this.userses = userses;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="clients")
    public Set getPositions() {
        return positions;
    }

    public void setPositions(Set positions) {
        this.positions = positions;
    }
    
    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    @Override
    public String toString() {
        return "Clients{" + "id=" + id + ", client_name=" + client_name + '}';
    }

}
