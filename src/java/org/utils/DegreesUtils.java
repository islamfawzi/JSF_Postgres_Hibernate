
package org.utils;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pojos.Degrees;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class DegreesUtils {


    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = null;
    
    public static List<Degrees> list(){
    
        String sql = "SELECT * FROM degrees ORDER BY id ASC";
        List<Degrees> degrees = null;
        
        try {
            
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Degrees.class);
            
            degrees = query.list();
            
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return degrees;
    }
    
    public static boolean update(Degrees degree){
        
        try {
            
            tx = session.beginTransaction();
            session.saveOrUpdate(degree);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            
            return false;
        }
    }
    
    public static boolean save(Degrees degree){
        
        try {
        
            tx = session.beginTransaction();
            session.save(degree);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        }
    
    }
    
    public static boolean delete(Degrees degree){
    
        try {
        
            tx = session.beginTransaction();
            session.delete(degree);
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
