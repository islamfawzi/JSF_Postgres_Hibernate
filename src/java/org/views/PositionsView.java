package org.views;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.pojos.Positions;
import org.utils.Message;
import org.utils.PositionsUtils;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class PositionsView {

    private Positions position;
    private List<Positions> positions;

    // set canEdit to true
    public void edit(Positions pos) {
        pos.setCanEdit(true);
    }

    // update Position
    public void update(Positions pos) {

        boolean updated = PositionsUtils.update(pos);

        if (updated) {
            Message.addMessage("Position Updated Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }

        pos.setCanEdit(false);
    }

    public void save() {
        
        if (this.position.getPosTitle().trim().length() > 0) {

            boolean added = PositionsUtils.save(this.position);

            if (added) {
                Message.addMessage("Position Added Successfully", "INFO");
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Position Title is Required", "WARN");
        }
    }

    public void delete(Positions pos) {

        boolean deleted = PositionsUtils.delete(pos);

        if (deleted) {
            Message.addMessage("Position Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }

    @PostConstruct
    private void init(){
        this.position = new Positions();
    }
    
    public List<Positions> getPositions() {

        positions = new ArrayList<Positions>();
        positions = PositionsUtils.list();
        return positions;
    }

    public void setPositions(List<Positions> positions) {
        this.positions = positions;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

}
