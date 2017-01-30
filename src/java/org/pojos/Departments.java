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
 * Departments generated by hbm2java
 */
@Entity
@Table(name="departments"
    ,schema="public"
)
public class Departments  implements java.io.Serializable {


     private int id;
     private String deptTitle;
     private short deptStatus;
     private Set payrollTemplateses = new HashSet(0);
     private Set payrollProcesseses = new HashSet(0);
     private Set employeeses = new HashSet(0);

    public Departments() {
    }

	
    public Departments(int id, String deptTitle, short deptStatus) {
        this.id = id;
        this.deptTitle = deptTitle;
        this.deptStatus = deptStatus;
    }
    public Departments(int id, String deptTitle, short deptStatus, Set payrollTemplateses, Set payrollProcesseses, Set employeeses) {
       this.id = id;
       this.deptTitle = deptTitle;
       this.deptStatus = deptStatus;
       this.payrollTemplateses = payrollTemplateses;
       this.payrollProcesseses = payrollProcesseses;
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

    
    @Column(name="dept_title", nullable=false)
    public String getDeptTitle() {
        return this.deptTitle;
    }
    
    public void setDeptTitle(String deptTitle) {
        this.deptTitle = deptTitle;
    }

    
    @Column(name="dept_status", nullable=false)
    public short getDeptStatus() {
        return this.deptStatus;
    }
    
    public void setDeptStatus(short deptStatus) {
        this.deptStatus = deptStatus;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="departments")
    public Set getPayrollTemplateses() {
        return this.payrollTemplateses;
    }
    
    public void setPayrollTemplateses(Set payrollTemplateses) {
        this.payrollTemplateses = payrollTemplateses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="departments")
    public Set getPayrollProcesseses() {
        return this.payrollProcesseses;
    }
    
    public void setPayrollProcesseses(Set payrollProcesseses) {
        this.payrollProcesseses = payrollProcesseses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="departments")
    public Set getEmployeeses() {
        return this.employeeses;
    }
    
    public void setEmployeeses(Set employeeses) {
        this.employeeses = employeeses;
    }




}


