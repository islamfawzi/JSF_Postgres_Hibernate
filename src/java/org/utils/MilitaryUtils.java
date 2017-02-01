
package org.utils;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pojos.MilitaryStatus;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class MilitaryUtils {


    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = null;
    
    public static List<MilitaryStatus> list(){
    
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
        
            tx = session.beginTransaction();
            session.save(militaryStatus);
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
