
package org.views;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.pojos.Degrees;
import org.utils.DegreesUtils;
import org.utils.Message;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class DegreesView {

    private Degrees degree;
    private List<Degrees> degrees;

    // set canEdit to true
    public void edit(Degrees degree) {
        degree.setCanEdit(true);
    }

    // update Degree
    public void update(Degrees degree) {

        boolean updated = DegreesUtils.update(degree);

        if (updated) {
            Message.addMessage(degree.getDegreeTitle()+ " degree Updated Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }

        degree.setCanEdit(false);
    }

    public void save() {
        
        if (this.degree.getDegreeTitle().trim().length() > 0) {

            boolean added = DegreesUtils.save(this.degree);

            if (added) {
                Message.addMessage(this.degree.getDegreeTitle()+ " degree Added Successfully", "INFO");
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Degree Title is Required", "WARN");
        }
    }

    public void delete(Degrees degree) {

        boolean deleted = DegreesUtils.delete(degree);

        if (deleted) {
            Message.addMessage(degree.getDegreeTitle()+ " degree Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }

    @PostConstruct
    private void init(){
        this.degree = new Degrees();
    }

    public Degrees getDegree() {
        return degree;
    }

    public void setDegree(Degrees degree) {
        this.degree = degree;
    }

    public List<Degrees> getDegrees() {
        degrees = new ArrayList<>();
        degrees = DegreesUtils.list();
        return degrees;
    }

    public void setDegrees(List<Degrees> degrees) {
        this.degrees = degrees;
    }
    
}
