package org.pojos;
// Generated Jan 30, 2017 1:15:59 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PayrollTemplatesItems generated by hbm2java
 */
@Entity
@Table(name="payroll_templates_items"
    ,schema="public"
)
public class PayrollTemplatesItems  implements java.io.Serializable {


     private int id;
     private PayrollItems payrollItems;
     private PayrollTemplates payrollTemplates;

    public PayrollTemplatesItems() {
    }

	
    public PayrollTemplatesItems(int id) {
        this.id = id;
    }
    public PayrollTemplatesItems(int id, PayrollItems payrollItems, PayrollTemplates payrollTemplates) {
       this.id = id;
       this.payrollItems = payrollItems;
       this.payrollTemplates = payrollTemplates;
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
    @JoinColumn(name="item_id")
    public PayrollItems getPayrollItems() {
        return this.payrollItems;
    }
    
    public void setPayrollItems(PayrollItems payrollItems) {
        this.payrollItems = payrollItems;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="template_id")
    public PayrollTemplates getPayrollTemplates() {
        return this.payrollTemplates;
    }
    
    public void setPayrollTemplates(PayrollTemplates payrollTemplates) {
        this.payrollTemplates = payrollTemplates;
    }




}


