
package org.utils;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
    
    public static List<Orgs> list(){
    
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
    
    public static boolean update(Orgs org){
        
        try {
            
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
        
            tx = session.beginTransaction();
            session.save(org);
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
