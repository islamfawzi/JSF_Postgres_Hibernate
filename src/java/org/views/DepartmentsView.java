
package org.views;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.pojos.Departments;
import org.utils.DepartmentsUtils;
import org.utils.Message;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class DepartmentsView {

    private Departments department;
    private List<Departments> departments;

    // set canEdit to true
    public void edit(Departments dept) {
        dept.setCanEdit(true);
    }
    
    // update Position
    public void update(Departments dept) {

        boolean updated = DepartmentsUtils.update(dept);

        if (updated) {
            Message.addMessage("Department Updated Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }

        dept.setCanEdit(false);
    }
    
    public void save() {
        
        if (this.department.getDeptTitle().trim().length() > 0) {

            boolean added = DepartmentsUtils.save(this.department);

            if (added) {
                Message.addMessage("Department Added Successfully", "INFO");
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Department Title is Required", "WARN");
        }
    }
    
    public void delete(Departments dept) {

        boolean deleted = DepartmentsUtils.delete(dept);

        if (deleted) {
            Message.addMessage("Department Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }
    
    @PostConstruct
    private void init(){
        this.department = new Departments();
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public List<Departments> getDepartments() {
        departments = new ArrayList<Departments>();
        departments = DepartmentsUtils.list();
        return departments;
    }

    public void setDepartments(List<Departments> departments) {
        this.departments = departments;
    }
    
    
    
}
