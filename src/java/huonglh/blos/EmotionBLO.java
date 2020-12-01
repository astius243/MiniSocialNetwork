package huonglh.blos;

import huonglh.entities.Article;
import huonglh.entities.Emotion;
import java.io.Serializable;
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
public class EmotionBLO implements Serializable {

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

    public List getEmotion(Article article) {
        EntityManager em = emf.createEntityManager();
        String jptl = "Select emotion From Emotion emotion Where emotion.articleID.articleID = :articleID";
        Query query = em.createQuery(jptl, Emotion.class);
        query.setParameter("articleID", article.getArticleID());
        return query.getResultList();
    }

    public long getLikeCount(Article article) {
        EntityManager em = emf.createEntityManager();
        String jptl = "Select Count(Emotion) From Emotion emotion Where emotion.articleID.articleID = :articleID and emotion.emotion = :emotion";
        Query query = em.createQuery(jptl);
        query.setParameter("articleID", article.getArticleID());
        query.setParameter("emotion", "LIKE");
        return (long) query.getSingleResult();
    }

    public long getDislikeCount(Article article) {
        EntityManager em = emf.createEntityManager();
        String jptl = "Select Count(Emotion) From Emotion emotion Where emotion.articleID.articleID = :articleID and emotion.emotion = :emotion";
        Query query = em.createQuery(jptl);
        query.setParameter("articleID", article.getArticleID());
        query.setParameter("emotion", "DISLIKE");
        return (long) query.getSingleResult();
    }

    public Emotion checkEmotion(String email, int articleID) {
        EntityManager em = emf.createEntityManager();
        String jptl = "Select EmotionID, Emotion, ArticleID, Email From Emotion Where ArticleID = ? and Email = ?";
        Query query = em.createNativeQuery(jptl, Emotion.class);
        query.setParameter("1", articleID);
        query.setParameter("2", email);
        try {
            return (Emotion) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean updateEmotion(Emotion emotion) {
        EntityManager em = emf.createEntityManager();
        Emotion e = em.find(Emotion.class, emotion.getEmotionID());
        if (e != null) {
            em.getTransaction().begin();
            em.merge(emotion);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    public boolean insertEmotion(Emotion emotion) {
        EntityManager em = emf.createEntityManager();
        String jptl = "Insert Into Emotion(ArticleID, Email, Emotion) Values(?,?,?)";
        Query query = em.createNativeQuery(jptl);
        em.getTransaction().begin();
        query.setParameter("1", emotion.getArticleID().getArticleID());
        query.setParameter("2", emotion.getEmail().getEmail());
        query.setParameter("3", emotion.getEmotion());
        boolean check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }

    public boolean deleteEmotions(Article article) {
        EntityManager em = emf.createEntityManager();
        String jptl = "Delete From Emotion emotion Where emotion.articleID.articleID = :articleID";
        Query query = em.createQuery(jptl);
        em.getTransaction().begin();
        query.setParameter("articleID", article.getArticleID());
        boolean check = query.executeUpdate() >= 0;
        em.getTransaction().commit();
        return check;
    }
}
