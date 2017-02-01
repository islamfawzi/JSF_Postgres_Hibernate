package org.views;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.pojos.MaritalStatus;
import org.utils.MaritalUtils;
import org.utils.Message;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class MaritalView {

    private MaritalStatus marital_status;
    private List<MaritalStatus> marital_statuses;

    // set canEdit to true
    public void edit(MaritalStatus marital_status) {
        marital_status.setCanEdit(true);
    }
    
    // update Position
    public void update(MaritalStatus marital_status) {

        boolean updated = MaritalUtils.update(marital_status);

        if (updated) {
            Message.addMessage(marital_status.getMaritalTitle() + " status Updated Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }

        marital_status.setCanEdit(false);
    }
    
    public void save() {
        
        if (this.marital_status.getMaritalTitle().trim().length() > 0) {

            boolean added = MaritalUtils.save(this.marital_status);

            if (added) {
                Message.addMessage(this.marital_status.getMaritalTitle() + " status Added Successfully", "INFO");
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Marital Status Title is Required", "WARN");
        }
    }
    
    public void delete(MaritalStatus marital_status) {

        boolean deleted = MaritalUtils.delete(marital_status);

        if (deleted) {
            Message.addMessage(marital_status.getMaritalTitle() + " status Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }
    
    @PostConstruct
    private void init(){
        this.marital_status = new MaritalStatus();
    }

    public MaritalStatus getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(MaritalStatus marital_status) {
        this.marital_status = marital_status;
    }

    public List<MaritalStatus> getMarital_statuses() {
        marital_statuses = new ArrayList<>();
        marital_statuses = MaritalUtils.list();
        return marital_statuses;
    }

    public void setMarital_statuses(List<MaritalStatus> marital_statuses) {
        this.marital_statuses = marital_statuses;
    }
    
    
}
