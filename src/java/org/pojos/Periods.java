package org.pojos;
// Generated Feb 6, 2017 5:18:07 PM by Hibernate Tools 4.3.1


import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Periods generated by hbm2java
 */
@Entity
@Table(name="periods"
    ,schema="public"
)
public class Periods  implements java.io.Serializable {


     private int id;
     private Calender calender;
     private Clients clients;
     private Orgs orgs;
     private Date startDate;
     private Date endDate;
     private Character status;
     private Date transDate;
     private Set payrollProcesseses = new HashSet(0);
     private Set processesAmountses = new HashSet(0);

    public Periods() {
    }

	
    public Periods(int id) {
        this.id = id;
    }
    public Periods(int id, Calender calender, Clients clients, Orgs orgs, Date startDate, Date endDate, Character status, Date transDate, Set payrollProcesseses, Set processesAmountses) {
       this.id = id;
       this.calender = calender;
       this.clients = clients;
       this.orgs = orgs;
       this.startDate = startDate;
       this.endDate = endDate;
       this.status = status;
       this.transDate = transDate;
       this.payrollProcesseses = payrollProcesseses;
       this.processesAmountses = processesAmountses;
    }
   
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="calender_id")
    public Calender getCalender() {
        return this.calender;
    }
    
    public void setCalender(Calender calender) {
        this.calender = calender;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="client_id")
    public Clients getClients() {
        return this.clients;
    }
    
    public void setClients(Clients clients) {
        this.clients = clients;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="org_id")
    public Orgs getOrgs() {
        return this.orgs;
    }
    
    public void setOrgs(Orgs orgs) {
        this.orgs = orgs;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_date", length=29)
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="end_date", length=29)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    @Column(name="status", length=1)
    public Character getStatus() {
        return this.status;
    }
    
    public void setStatus(Character status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="trans_date", length=29)
    public Date getTransDate() {
        return this.transDate;
    }
    
    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="periods")
    public Set getPayrollProcesseses() {
        return this.payrollProcesseses;
    }
    
    public void setPayrollProcesseses(Set payrollProcesseses) {
        this.payrollProcesseses = payrollProcesseses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="periods")
    public Set getProcessesAmountses() {
        return this.processesAmountses;
    }
    
    public void setProcessesAmountses(Set processesAmountses) {
        this.processesAmountses = processesAmountses;
    }




}


