
package org.utils;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.pojos.Calender;
import org.pojos.Orgs;
import org.pojos.Users;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class CalendarsUtils {

    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = null;
    
    public static List<Calender> list(Users user, Orgs org){
    
        try {
            Criteria cr = session.createCriteria(Calender.class);
            
            if(user.getId() != 0){
                // System User
                if(org != null){
                    // Orgs Positions
                    
                    // ( orgs = null && clients = client) || orgs = org || client = null
                    Criterion all_orgs_c = Restrictions.isNull("orgs");
                    Criterion client_c = Restrictions.eq("clients", user.getClients());
                    LogicalExpression andExp = Restrictions.and(all_orgs_c, client_c);
                    
                    // same Org
                    Criterion org_c = Restrictions.eq("orgs", org);
                    LogicalExpression orgExp = Restrictions.or(org_c, andExp);
                    
                    // all clients
                    Criterion all_clients = Restrictions.isNull("clients");
                    LogicalExpression orgExp2 = Restrictions.or(all_clients, orgExp);
                    
                    cr.add(orgExp2);
                }
                else{
                    // Client Positions
                    
                   Criterion client_c = Restrictions.eq("clients", user.getClients());
                   Criterion all_clients = Restrictions.isNull("clients");
                   LogicalExpression orgExp = Restrictions.or(client_c, all_clients);
                   
                   cr.add(orgExp);
                }
                
            }
            
            cr.addOrder(Order.asc("id"));
            
            return cr.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Calender get(int calendar_id){
    
        try {
            Criteria cr = session.createCriteria(Calender.class);
            cr.add(Restrictions.eq("id", calendar_id));
            
            return (Calender) cr.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean save(Calender calendar){
        
        try {
            session.clear();
            
            tx = session.beginTransaction();
            session.saveOrUpdate(calendar);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        }
    
    }
    
    public static boolean delete(Calender calendar){
    
        try {
        
            tx = session.beginTransaction();
            session.delete(calendar);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            
            return false;
        }
    }
    
    @PreDestroy
    private static void destroy() {
        session.flush();  
        session.close();
    }
    
}
