package org.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Session;
import org.pojos.Test;
import org.utils.HibernateUtil;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class UserBean {


    private Test test;
    private Session session;

    public String add(){
    
        try {
            session.beginTransaction();
            
            session.save(test);
            
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }
    
    
    @PostConstruct
    private void init(){
    
        test = new Test();
        try {
            session = HibernateUtil.getSessionFactory().openSession();        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @PreDestroy
    private void destroy(){
        try {
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
    
    public UserBean() {
    }
    
}
