package huonglh.blos;

import huonglh.entities.Account;
import huonglh.utilities.HashingCode;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Hau Huong
 */
public class AccountBLO implements Serializable {

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

    public Account checkLogin(String email, String password) throws Exception {
        EntityManager em = emf.createEntityManager();
        String jptl = "Select a From Account a Where a.email = :email and a.password = :password";
        String encode = HashingCode.encode(password);
        Query query = em.createQuery(jptl);
        query.setParameter("email", email);
        query.setParameter("password", encode);
        try {
            return (Account) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public boolean createAccount(Account acc) throws Exception {
        EntityManager em = emf.createEntityManager();
        Account account = em.find(Account.class, acc.getEmail());
        if (account == null) {
            String encode = HashingCode.encode(acc.getPassword());
            acc.setPassword(encode);
            em.getTransaction().begin();
            em.persist(acc);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    public boolean updateStatus(Account acc) throws Exception {
        EntityManager em = emf.createEntityManager();
        Account account = em.find(Account.class, acc.getEmail());
        if (account != null) {
            em.getTransaction().begin();
            em.merge(acc);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
}
