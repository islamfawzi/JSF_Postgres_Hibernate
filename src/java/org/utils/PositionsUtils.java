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
import org.pojos.Orgs;
import org.pojos.Positions;
import org.pojos.Users;

/**
 *
 * @author islam
 */
@ManagedBean
@RequestScoped
public class PositionsUtils {

    private static Session session = HibernateUtil.getSessionFactory().openSession();
    private static Transaction tx = null;

    public static List<Positions> list2() {

        String sql = "SELECT * FROM positions ORDER BY id ASC";
        List<Positions> positions = null;

        try {

            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Positions.class);

            positions = query.list();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return positions;
    }
    
    public static Positions get(int pos_id){
        
        try {
            Criteria cr = session.createCriteria(Positions.class);
            cr.add(Restrictions.eq("id", pos_id));
            
            return (Positions) cr.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Positions> list(Users user, Orgs org) {

        List<Positions> positions = new ArrayList<>();
        try {
            Criteria cr = session.createCriteria(Positions.class);
            
            if(user.getId() != 0){
                // System User
                if(org != null){
                    // Orgs Positions
                    
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
                    // Client Positions
                   cr.add(Restrictions.eq("clients", user.getClients()));
                }
            }
            
            cr.addOrder(Order.asc("id"));
            positions = cr.list();

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return positions;
    }

    public static boolean update(Positions position) {

        try {

            tx = session.beginTransaction();
            session.saveOrUpdate(position);
            tx.commit();

            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

            return false;
        }
    }

    public static boolean save(Positions position) {

        try {
            session.clear();
            
            tx = session.beginTransaction();
            session.saveOrUpdate(position);
            tx.commit();

            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }

    }

    public static boolean delete(Positions position) {

        try {

            tx = session.beginTransaction();
            session.delete(position);
            tx.commit();

            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

            return false;
        }
    }

    @PreDestroy
    private static void destroy() {
        session.close();
    }

}
