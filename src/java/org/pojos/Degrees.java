package org.pojos;
// Generated Jan 30, 2017 1:15:59 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Degrees generated by hbm2java
 */
@Entity
@Table(name="degrees"
    ,schema="public"
)
public class Degrees  implements java.io.Serializable {


     private int id;
     private String degreeTitle;
     private short degreeStatus;
     private Set employeeses = new HashSet(0);

    public Degrees() {
    }

	
    public Degrees(int id, String degreeTitle, short degreeStatus) {
        this.id = id;
        this.degreeTitle = degreeTitle;
        this.degreeStatus = degreeStatus;
    }
    public Degrees(int id, String degreeTitle, short degreeStatus, Set employeeses) {
       this.id = id;
       this.degreeTitle = degreeTitle;
       this.degreeStatus = degreeStatus;
       this.employeeses = employeeses;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name="degree_title", nullable=false)
    public String getDegreeTitle() {
        return this.degreeTitle;
    }
    
    public void setDegreeTitle(String degreeTitle) {
        this.degreeTitle = degreeTitle;
    }

    
    @Column(name="degree_status", nullable=false)
    public short getDegreeStatus() {
        return this.degreeStatus;
    }
    
    public void setDegreeStatus(short degreeStatus) {
        this.degreeStatus = degreeStatus;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="degrees")
    public Set getEmployeeses() {
        return this.employeeses;
    }
    
    public void setEmployeeses(Set employeeses) {
        this.employeeses = employeeses;
    }




}

