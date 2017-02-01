package org.pojos;
// Generated Jan 30, 2017 1:15:59 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MilitaryStatus generated by hbm2java
 */
@Entity
@Table(name = "military_status", schema = "public"
)
public class MilitaryStatus implements java.io.Serializable {

    private int id;
    private String militaryTitle;
    private boolean militaryStatus = true;
    private Set employeeses = new HashSet(0);
    private boolean canEdit;

    public MilitaryStatus() {
    }

    public MilitaryStatus(int id, String militaryTitle, boolean militaryStatus) {
        this.id = id;
        this.militaryTitle = militaryTitle;
        this.militaryStatus = militaryStatus;
    }

    public MilitaryStatus(int id, String militaryTitle, boolean militaryStatus, Set employeeses) {
        this.id = id;
        this.militaryTitle = militaryTitle;
        this.militaryStatus = militaryStatus;
        this.employeeses = employeeses;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "military_title", nullable = false)
    public String getMilitaryTitle() {
        return this.militaryTitle;
    }

    public void setMilitaryTitle(String militaryTitle) {
        this.militaryTitle = militaryTitle;
    }

    @Column(name = "military_status", nullable = false)
    public boolean getMilitaryStatus() {
        return this.militaryStatus;
    }

    public void setMilitaryStatus(boolean militaryStatus) {
        this.militaryStatus = militaryStatus;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "militaryStatus")
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
