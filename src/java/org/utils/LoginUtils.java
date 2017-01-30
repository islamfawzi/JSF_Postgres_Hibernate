package org.utils;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;
import org.pojos.Employees;
import org.pojos.Users;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class LoginUtils {

    private static Session session = HibernateUtil.getSessionFactory().openSession();

    public static void save(Users user) {

        try {
            
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

    }
    
    @PreDestroy
    private static void destroy() {
        session.close();
    }
}
