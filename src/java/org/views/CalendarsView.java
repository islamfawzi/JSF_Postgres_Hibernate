package org.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.helpers.ClientOrgBean;
import org.helpers.Message;
import org.helpers.UserSession;
import org.pojos.Calender;
import org.pojos.Clients;
import org.pojos.Orgs;
import org.utils.CalendarsUtils;
import org.utils.ClientsUtils;
import org.utils.OrgsUtils;

/**
 *
 * @author islam
 */
@ManagedBean
@ViewScoped
public class CalendarsView {

    private Calender calendar;
    private Calender update_calendar;

    private List<Calender> calendars;
    private List<Integer> days;
    private List<String> weekDays;

    private int activeIndex = 0;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    @ManagedProperty(value = "#{clientOrgBean}")
    private ClientOrgBean clientOrgBean;

    public void edit(Calender calendar) {

        this.update_calendar = CalendarsUtils.get(calendar.getId());

        Clients client = update_calendar.getClients();
        Orgs org = update_calendar.getOrgs();

        clientOrgBean.setClient_id(client != null ? client.getId() : 0);
        clientOrgBean.setOrg_id(org != null ? org.getId() : 0);

        activeIndex = 2;
    }

    public String save(Calender calendar) {

        if (calendar.getCalenderTitle().trim().length() > 0) {
            System.out.println(">>>>>>>>>>>>>>>>>>> ");
            System.out.println(calendar);
            
            Clients client = (clientOrgBean.getClient_id() == 0 ? userSession.getUser().getClients() : ClientsUtils.get(clientOrgBean.getClient_id()));
            Orgs org = (clientOrgBean.getOrg_id() == 0 ? userSession.getOrg() : OrgsUtils.get(clientOrgBean.getOrg_id()));

            calendar.setClients(client);
            calendar.setOrgs(org);

            boolean added = CalendarsUtils.save(calendar);

            if (added) {
                Message.addMessage(calendar.getCalenderTitle() + " saved Successfully", "INFO");
                
                return "calendars?faces-redirect=true";
            } else {
                Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
            }
        } else {
            Message.addMessage("Calendar Title is Required", "WARN");
        }
        return null;
    }

    public void delete(Calender calendar) {

        boolean deleted = CalendarsUtils.delete(calendar);

        if (deleted) {
            Message.addMessage(calendar.getCalenderTitle() + " Deleted Successfully", "INFO");
        } else {
            Message.addMessage("Oops! something wrong happened, please try again!.", "ERROR");
        }
    }

    @PostConstruct
    private void init() {
        this.calendar = new Calender();
        this.update_calendar = new Calender();
    }

    public Calender getCalendar() {
        return calendar;
    }

    public void setCalendar(Calender calendar) {
        this.calendar = calendar;
    }

    public Calender getUpdate_calendar() {
        return update_calendar;
    }

    public void setUpdate_calendar(Calender update_calendar) {
        this.update_calendar = update_calendar;
    }

    public List<Calender> getCalendars() {
        return CalendarsUtils.list(userSession.getUser(), userSession.getOrg());
    }

    public void setCalendars(List<Calender> calendars) {
        this.calendars = calendars;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public ClientOrgBean getClientOrgBean() {
        return clientOrgBean;
    }

    public void setClientOrgBean(ClientOrgBean clientOrgBean) {
        this.clientOrgBean = clientOrgBean;
    }

    public List<Integer> getDays() {
        days = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            days.add(i);
        }
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public List<String> getWeekDays() {
        weekDays = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
        return weekDays;
    }

    public void setWeekDays(List<String> weekDays) {
        this.weekDays = weekDays;
    }
    
}
