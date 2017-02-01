
package org.views;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.pojos.Cities;
import org.utils.CitiesUtils;
import org.utils.Message;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class CitiesView {

    private Cities city;
    private List<Cities> cities;

    // set canEdit to true
    public void edit(Cities city) {
        city.setCanEdit(true);
    }
    
    // update City
    public void update(Cities city) {

        boolean updated = CitiesUtils.update(city);

        if (updated) {
            Message.addMessage(city.getCityTitle() + " city Updated Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }

        city.setCanEdit(false);
    }
    
    // insert new city
    public void save() {
        
        if (this.city.getCityTitle().trim().length() > 0) {

            boolean added = CitiesUtils.save(this.city);

            if (added) {
                Message.addMessage(city.getCityTitle() + " City Added Successfully", "INFO");
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("City Title is Required", "WARN");
        }
    }

    // delete City
    public void delete(Cities city) {

        boolean deleted = CitiesUtils.delete(city);

        if (deleted) {
            Message.addMessage(city.getCityTitle() + " City Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }
    
    
    @PostConstruct
    private void init(){
        this.city = new Cities();
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public List<Cities> getCities() {
        cities = new ArrayList<Cities>();
        cities = CitiesUtils.list();
        return cities;
    }

    public void setCities(List<Cities> cities) {
        this.cities = cities;
    }

}
