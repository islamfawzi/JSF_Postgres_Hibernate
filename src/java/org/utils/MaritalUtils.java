
package org.utils;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pojos.MaritalStatus;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class MaritalUtils {

    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = null;
    
    public static List<MaritalStatus> list(){
    
        String sql = "SELECT * FROM marital_status ORDER BY id ASC";
        List<MaritalStatus> marital_statuses = null;
        
        try {
            
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(MaritalStatus.class);
            
            marital_statuses = query.list();
            
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return marital_statuses;
    }
    
    public static boolean update(MaritalStatus marital_status){
        
        try {
            
            tx = session.beginTransaction();
            session.saveOrUpdate(marital_status);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            
            return false;
        }
    }
    
    public static boolean save(MaritalStatus marital_status){
        
        try {
        
            tx = session.beginTransaction();
            session.save(marital_status);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        }
    
    }
    
    public static boolean delete(MaritalStatus marital_status){
    
        try {
        
            tx = session.beginTransaction();
            session.delete(marital_status);
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
