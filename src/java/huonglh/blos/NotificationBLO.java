/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huonglh.blos;

import huonglh.entities.Notification;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Hau Huong
 */
public class NotificationBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiniSocialNetworkPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public boolean insertNotify(Notification noti) {
        EntityManager em = emf.createEntityManager();
        String jptl = "Insert Into Notification(Email, ArticleID, Date, [Content]) Values(?,?,?,?)";
        Query query = em.createNativeQuery(jptl);
        em.getTransaction().begin();
        query.setParameter("1", noti.getArticleID().getEmail().getEmail());
        query.setParameter("2", noti.getArticleID().getArticleID());
        query.setParameter("3", noti.getDate());
        query.setParameter("4", noti.getContent());
        boolean check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }
    
    public List getAllNotify(String email){
        EntityManager em = emf.createEntityManager();
        String jptl = "Select noti From Notification noti Where noti.email.email = :email";
        Query query = em.createQuery(jptl);
        query.setParameter("email", email);
        return query.getResultList();
    }
}
