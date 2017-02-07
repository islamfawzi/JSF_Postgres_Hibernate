package org.utils;

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
import org.pojos.Users;


/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class ClientsUtils {

    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = null;

    
    public static List<Clients> list(boolean active){
    
        String sql = "SELECT * FROM clients "
                   + (active ? "WHERE client_status = TRUE " : " ")
                   + "ORDER BY id ASC";
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
    
    // by Criteria
    public static Clients get(int id){
    
        Clients client = null;
        
        try {
            Criteria cr = session.createCriteria(Clients.class);
            cr.add(Restrictions.eq("id", id));
            client = (Clients) cr.uniqueResult();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return client;
    }
    
    public static boolean update(Clients client){
        
        try {
            session.clear();
            
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
        
        Integer client_id = 0;
        
        try {
            session.clear();
            
            tx = session.beginTransaction();
            client_id = (Integer) session.save(client);
            
            // create a default user for the new client
            if(client_id > 0){
                client.setId(client_id);
                Users user = new Users(client, client.getClientName(), client.getClientName(), true);
                session.save(user);
            }
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
