package org.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pojos.Employees;
import org.pojos.Users;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class UsersUtils {

    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = null;
    
    public static boolean login(String username, String password){
    
        String sql = "SELECT * FROM users WHERE username = :username AND status = 1";
        try {
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Users.class);
            query.setParameter("username", username);
            Users user = (Users) query.uniqueResult();
            
            if(user.getPassword().equals(password))
                return true;
            
        } catch (Exception e) {
            if (tx != null) 
                tx.rollback();
            e.printStackTrace();
        }
    
        return false;
    }
    
    
    public static void listUsers() {
        
        String sql = "SELECT * FROM users";
         
        try {
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Users.class);

            List users = query.list();

            
            for (Iterator iterator
                    = users.iterator(); iterator.hasNext();) {
                Users user = (Users) iterator.next();
                System.out.println("Username: " + user.getUsername());
                System.out.println("Password: " + user.getPassword());
                System.out.println("Status: " + user.getStatus());
                System.out.println("Last login: " + user.getLastLogin());
                System.out.println("Employee: " + user.getEmployees().getEmpFullname());
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Integer save(Users user) {
        
        Integer user_id = null;

        try {

            tx = session.beginTransaction();
            user_id = (Integer) session.save(user);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }

        return user_id;
    }

    @PreDestroy
    private static void destroy() {
        session.close();
    }
}
