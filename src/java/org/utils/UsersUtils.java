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
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
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
    
    public static Users login2(String username, String password){
    
        String sql = "SELECT * FROM users WHERE username = :username AND status = TRUE";
        try {
            session.clear();
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Users.class);
            query.setParameter("username", username);
            Users user = (Users) query.uniqueResult();
            
            if(user != null && user.getPassword().equals(password))
                return user;
            
        } catch (Exception e) {
            if (tx != null) 
                tx.rollback();
            e.printStackTrace();
        }
    
        return null;
    }
    
    public static Users login(String username, String password){
    
        try {
            Criteria cr = session.createCriteria(Users.class);
            Criterion u = Restrictions.eq("username", username);
            Criterion p = Restrictions.eq("password", password);
            Criterion active = Restrictions.eq("status", true);
            
            LogicalExpression Exp = Restrictions.and(u, p);
            Exp = Restrictions.and(Exp, active);
            
            cr.add(Exp);
            
            return (Users) cr.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static List<Users> list(boolean active){
    
        try {
            Criteria cr = session.createCriteria(Users.class);
            cr.add(Restrictions.gt("id", 0));
            if(active){
                cr.add(Restrictions.eq("status", true));
            }
            return cr.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static boolean save(Users user) {
        try {
            session.clear();
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            if (tx != null) 
                tx.rollback();
            e.printStackTrace();
            
            return false;
        }
    }
    
    public static Users get(int id){
    
        Users user = null;
        try {
            Criteria cr = session.createCriteria(Users.class);
            cr.add(Restrictions.eq("id", id));
            
            user = (Users) cr.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public static boolean delete(Users user){
    
        try {
            tx = session.beginTransaction();
            session.delete(user);
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
