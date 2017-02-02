
package org.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author islam
 */
@Entity
@Table(name = "orgs", schema = "public")
public class Orgs implements java.io.Serializable {
    
    
    private int id;
    private String org_name;
    private String org_desc;
    private boolean org_status;
    private Clients clients;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "org_name", nullable = false)
    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    @Column(name = "org_desc", nullable = false)
    public String getOrg_desc() {
        return org_desc;
    }

    public void setOrg_desc(String org_desc) {
        this.org_desc = org_desc;
    }

    @Column(name = "org_status")
    public boolean isOrg_status() {
        return org_status;
    }

    public void setOrg_status(boolean org_status) {
        this.org_status = org_status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }
    
    
}
