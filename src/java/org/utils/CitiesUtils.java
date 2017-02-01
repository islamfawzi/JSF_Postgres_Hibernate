package org.utils;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pojos.Cities;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class CitiesUtils {

    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = null;

    
    public static List<Cities> list(){
    
        String sql = "SELECT * FROM cities ORDER BY id ASC";
        List<Cities> cities = null;
        
        try {
            
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Cities.class);
            
            cities = query.list();
            
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return cities;
    }
    
    public static boolean update(Cities city){
        
        try {
            
            tx = session.beginTransaction();
            session.saveOrUpdate(city);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            
            return false;
        }
    }
    
    public static boolean save(Cities city){
        
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(city);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        }
    
    }
    
    public static boolean delete(Cities city){
    
        try {
        
            tx = session.beginTransaction();
            session.delete(city);
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
