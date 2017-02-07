
package org.utils;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.pojos.Departments;
import org.pojos.Orgs;
import org.pojos.Positions;
import org.pojos.Users;


/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class DepartmentsUtils {


    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = null;
    
    public static List<Departments> list(Users user, Orgs org) {

        List<Departments> departments = new ArrayList<>();
        try {
            Criteria cr = session.createCriteria(Departments.class);

            if(user.getId() != 0){
                // System User
                if(org != null){
                    // Orgs Departments
                    
                    // ( orgs = null && clients = client) || orgs = org || client = null
                    Criterion all_orgs_c = Restrictions.isNull("orgs");
                    Criterion client_c = Restrictions.eq("clients", user.getClients());
                    LogicalExpression andExp = Restrictions.and(all_orgs_c, client_c);
                    
                    // same Org
                    Criterion org_c = Restrictions.eq("orgs", org);
                    LogicalExpression orgExp = Restrictions.or(org_c, andExp);
                    
                    // all clients
                    Criterion all_clients = Restrictions.isNull("clients");
                    LogicalExpression orgExp2 = Restrictions.or(all_clients, orgExp);
                    
                    cr.add(orgExp2);
                }
                else{
                    // Client Departments
                   Criterion client_c = Restrictions.eq("clients", user.getClients());
                   Criterion all_c = Restrictions.isNull("clients");
                   
                   LogicalExpression clientExp = Restrictions.or(client_c, all_c);
                    
                   cr.add(clientExp);
                }
            }
            
            cr.addOrder(Order.asc("id"));
            departments = cr.list();

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }
    
    public static List<Departments> list2(){
    
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
    
    public static Departments get(int dept_id){
        
        try {
            Criteria cr = session.createCriteria(Departments.class);
            cr.add(Restrictions.eq("id", dept_id));
            
            return (Departments) cr.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
            session.clear();
            
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
