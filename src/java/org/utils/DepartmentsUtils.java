
package org.utils;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pojos.Departments;
import org.pojos.Positions;


/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class DepartmentsUtils {


    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = null;
    
    public static List<Departments> list(){
    
        String sql = "SELECT * FROM departments ORDER BY id ASC";
        List<Departments> departments = null;
        
        try {
            
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Departments.class);
            
            departments = query.list();
            
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return departments;
    }
    
    public static boolean update(Departments department){
        
        try {
            
            tx = session.beginTransaction();
            session.saveOrUpdate(department);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            
            return false;
        }
    }
    
    public static boolean save(Departments department){
        
        try {
        
            tx = session.beginTransaction();
            session.save(department);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        }
    
    }
    
    public static boolean delete(Departments department){
    
        try {
        
            tx = session.beginTransaction();
            session.delete(department);
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
