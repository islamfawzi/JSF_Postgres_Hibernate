package org.pojos;
// Generated Jan 30, 2017 1:15:59 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PayrollEmployeeItems generated by hbm2java
 */
@Entity
@Table(name="payroll_employee_items"
    ,schema="public"
)
public class PayrollEmployeeItems  implements java.io.Serializable {


     private int id;
     private Employees employees;
     private PayrollItems payrollItems;
     private BigDecimal amount;
     private Short status;

    public PayrollEmployeeItems() {
    }

	
    public PayrollEmployeeItems(int id) {
        this.id = id;
    }
    public PayrollEmployeeItems(int id, Employees employees, PayrollItems payrollItems, BigDecimal amount, Short status) {
       this.id = id;
       this.employees = employees;
       this.payrollItems = payrollItems;
       this.amount = amount;
       this.status = status;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="emp_id")
    public Employees getEmployees() {
        return this.employees;
    }
    
    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="item_id")
    public PayrollItems getPayrollItems() {
        return this.payrollItems;
    }
    
    public void setPayrollItems(PayrollItems payrollItems) {
        this.payrollItems = payrollItems;
    }

    
    @Column(name="amount", precision=131089, scale=0)
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    
    @Column(name="status")
    public Short getStatus() {
        return this.status;
    }
    
    public void setStatus(Short status) {
        this.status = status;
    }




}


