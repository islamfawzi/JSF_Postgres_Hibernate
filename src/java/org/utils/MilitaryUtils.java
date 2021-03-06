
package org.utils;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.pojos.MaritalStatus;
import org.pojos.MilitaryStatus;
import org.pojos.Orgs;
import org.pojos.Users;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class MilitaryUtils {


    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = null;
    
    public static List<MilitaryStatus> list(Users user, Orgs org){
    
        try {
            Criteria cr = session.createCriteria(MilitaryStatus.class);
            
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
    
    public static List<MilitaryStatus> list2(){
    
        String sql = "SELECT * FROM military_status ORDER BY id ASC";
        List<MilitaryStatus> militaryStatuses = null;
        
        try {
            
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(MilitaryStatus.class);
            
            militaryStatuses = query.list();
            
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return militaryStatuses;
    }
    
    public static MilitaryStatus get(int military_id){
    
        try {
            Criteria cr = session.createCriteria(MilitaryStatus.class);
            cr.add(Restrictions.eq("id", military_id));
            
            return (MilitaryStatus) cr.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean update(MilitaryStatus militaryStatus){
        
        try {
            
            tx = session.beginTransaction();
            session.saveOrUpdate(militaryStatus);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            
            return false;
        }
    }
    
    public static boolean save(MilitaryStatus militaryStatus){
        
        try {
            session.clear();
            tx = session.beginTransaction();
            session.saveOrUpdate(militaryStatus);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        }
    
    }
    
    public static boolean delete(MilitaryStatus militaryStatus){
    
        try {
        
            tx = session.beginTransaction();
            session.delete(militaryStatus);
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
        session.close();
    }
    
    
}
