package org.pojos;
// Generated Jan 30, 2017 1:15:59 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * ActionsTrans generated by hbm2java
 */
@Entity
@Table(name="actions_trans"
    ,schema="public"
)
public class ActionsTrans  implements java.io.Serializable {


     private int id;
     private Actions actions;
     private Employees employees;
     private Integer actionId;
     private Date createdAt;
     private BigDecimal value;
     private Short status;
     private Date updatedAt;

    public ActionsTrans() {
    }

	
    public ActionsTrans(Actions actions, Date createdAt, Date updatedAt) {
        this.actions = actions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public ActionsTrans(Actions actions, Employees employees, Integer actionId, Date createdAt, BigDecimal value, Short status, Date updatedAt) {
       this.actions = actions;
       this.employees = employees;
       this.actionId = actionId;
       this.createdAt = createdAt;
       this.value = value;
       this.status = status;
       this.updatedAt = updatedAt;
    }
   
     @GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="actions"))@Id @GeneratedValue(generator="generator")

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public Actions getActions() {
        return this.actions;
    }
    
    public void setActions(Actions actions) {
        this.actions = actions;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="emp_id")
    public Employees getEmployees() {
        return this.employees;
    }
    
    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    
    @Column(name="action_id")
    public Integer getActionId() {
        return this.actionId;
    }
    
    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable=false, length=29)
    public Date getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    
    @Column(name="value", precision=131089, scale=0)
    public BigDecimal getValue() {
        return this.value;
    }
    
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    
    @Column(name="status")
    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at", nullable=false, length=29)
    public Date getUpdatedAt() {
        return this.updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }




}


