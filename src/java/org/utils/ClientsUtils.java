package org.utils;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pojos.Clients;


/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class ClientsUtils {

    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = null;

    
    public static List<Clients> list(){
    
        String sql = "SELECT * FROM clients ORDER BY id ASC";
        List<Clients> clients = null;
        
        try {
            
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Clients.class);
            
            clients = query.list();
            
            tx.commit();
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return clients;
    }
    
    public static boolean update(Clients client){
        
        try {
            
            tx = session.beginTransaction();
            session.saveOrUpdate(client);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            
            return false;
        }
    }
    
    public static boolean save(Clients client){
        
        session.clear();
        
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(client);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
            return false;
        }
    
    }
    
    public static boolean delete(Clients client){
    
        try {
        
            tx = session.beginTransaction();
            session.delete(client);
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
