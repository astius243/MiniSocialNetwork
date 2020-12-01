package huonglh.blos;

import huonglh.entities.Article;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
public class ArticleBLO implements Serializable {

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

    public long getCountArticle() {
        EntityManager em = emf.createEntityManager();
        String jptl = "Select Count(article.articleID) from Article article Where article.statusID.statusID = :statusID";
        Query query = em.createQuery(jptl);
        query.setParameter("statusID", 2);
        return (long) query.getSingleResult();
    }

    public List getArticleFromTo(int pageNo, int pageSize) {
        EntityManager em = emf.createEntityManager();
        String jptl = "Select article from Article article Where article.statusID.statusID = :status Order by article.date ";
        Query query = em.createQuery(jptl);
        query.setParameter("status", 2);
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public Article viewArticle(int articleID) {
        EntityManager em = emf.createEntityManager();

        String jptl = "Article.findByArticleID";
        Query query = em.createNamedQuery(jptl);
        query.setParameter("articleID", articleID);
        try {
            return (Article) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List searchArticleByTitleFromTo(int pageNo, int pageSize, String search) {
        EntityManager em = emf.createEntityManager();
        String jptl = "Select article From Article article Where article.description LIKE :description and article.statusID.statusID = :statusID";
        Query query = em.createQuery(jptl);
        query.setParameter("description", "%" + search + "%");
        query.setParameter("statusID", 2);
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public long getCountArticleByTitle(String search) {
        EntityManager em = emf.createEntityManager();
        String jptl = "Select Count(article.articleID) from Article article Where article.statusID.statusID = :statusID and article.description LIKE :description";
        Query query = em.createQuery(jptl);
        query.setParameter("statusID", 2);
        query.setParameter("description", "%" + search + "%");
        return (long) query.getSingleResult();
    }

    public boolean insertArticle(Article art) {
        EntityManager em = emf.createEntityManager();

        String jptl = "Insert Into Article(Email, Title, Description, Image, Date, StatusID) Values(?,?,?,?,?,?)";
        Query query = em.createNativeQuery(jptl);

        em.getTransaction().begin();
        query.setParameter("1", art.getEmail().getEmail());
        query.setParameter("2", art.getTitle());
        query.setParameter("3", art.getDescription());
        query.setParameter("4", art.getImage());
        query.setParameter("5", art.getDate());
        query.setParameter("6", art.getStatusID().getStatusID());
        boolean check = query.executeUpdate() > 0;
        em.getTransaction().commit();

        return check;
    }

    public boolean deleteArticle(int articleID) {
        EntityManager em = emf.createEntityManager();
        String jqtl = "Update Article Set StatusID = ? Where ArticleID = ?";
        Query query = em.createNativeQuery(jqtl);
        em.getTransaction().begin();
        query.setParameter("1", 3);
        query.setParameter("2", articleID);
        boolean check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }
}
