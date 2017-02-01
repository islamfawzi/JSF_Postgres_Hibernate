package org.views;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.pojos.MilitaryStatus;
import org.utils.Message;
import org.utils.MilitaryUtils;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class MilitaryView {

    private MilitaryStatus military_status;
    private List<MilitaryStatus> military_statuses;

    // set canEdit to true
    public void edit(MilitaryStatus military) {
        military.setCanEdit(true);
    }

    // update Military Status
    public void update(MilitaryStatus military) {

        boolean updated = MilitaryUtils.update(military);

        if (updated) {
            Message.addMessage(military.getMilitaryTitle() + " status Updated Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }

        military.setCanEdit(false);
    }

    public void save() {

        if (this.military_status.getMilitaryTitle().trim().length() > 0) {

            boolean added = MilitaryUtils.save(this.military_status);

            if (added) {
                Message.addMessage(this.military_status.getMilitaryTitle() + " status Added Successfully", "INFO");
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Military Status Title is Required", "WARN");
        }
    }

    public void delete(MilitaryStatus military) {

        boolean deleted = MilitaryUtils.delete(military);

        if (deleted) {
            Message.addMessage(military.getMilitaryTitle() + " status Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }

    @PostConstruct
    private void init() {
        this.military_status = new MilitaryStatus();
    }

    public MilitaryStatus getMilitary_status() {
        return military_status;
    }

    public void setMilitary_status(MilitaryStatus military_status) {
        this.military_status = military_status;
    }

    public List<MilitaryStatus> getMilitary_statuses() {
        military_statuses = new ArrayList<>();
        military_statuses = MilitaryUtils.list();
        return military_statuses;
    }

    public void setMilitary_statuses(List<MilitaryStatus> military_statuses) {
        this.military_statuses = military_statuses;
    }

    

}
