
package org.utils;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.pojos.Clients;
import org.pojos.Orgs;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class OrgsUtils {

private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = null;
    
    public static List<Orgs> list(boolean active){
         
         List<Orgs> orgs = new ArrayList<>();
         try {
             Criteria cr = session.createCriteria(Orgs.class);
             
             if(active)
                 cr.add(Restrictions.eq("orgStatus", true));
             
             orgs = cr.list();
             
         } catch (Exception e) {
             e.printStackTrace();
         }
         
         return orgs;
    }
    
    public static List<Orgs> list2(){
    
        String sql = "SELECT * FROM orgs ORDER BY id ASC";
        List<Orgs> orgs = null;
        
        try {
            
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Orgs.class);
            
            orgs = query.list();
            
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return orgs;
    }
    
    public static List<Orgs> getOrgsPerClient(Clients client){
    
        try {
            Criteria cr = session.createCriteria(Orgs.class);
            
            cr.add(Restrictions.eq("clients", client));
            cr.add(Restrictions.eq("orgStatus", true));
            
            return cr.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Orgs get(int id){
        Orgs org = null;
        try {
            Criteria cr = session.createCriteria(Orgs.class);
            cr.add(Restrictions.eq("id", id));
            
            org = (Orgs) cr.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return org;
    }
    
    public static boolean update(Orgs org){
        
        try {
            session.clear();
            
            tx = session.beginTransaction();
            session.saveOrUpdate(org);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            
            return false;
        }
    }
    
    public static boolean save(Orgs org){
        
        try {
            session.clear();
            
            tx = session.beginTransaction();
            session.saveOrUpdate(org);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        }
    
    }
    
    public static boolean delete(Orgs org){
    
        try {
        
            tx = session.beginTransaction();
            session.delete(org);
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
