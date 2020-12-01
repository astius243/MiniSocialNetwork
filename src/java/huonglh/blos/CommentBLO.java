package huonglh.blos;

import huonglh.entities.Article;
import huonglh.entities.Comment;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class CommentBLO implements Serializable {

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

    public boolean insertComment(Comment cmt) throws ParseException {
        EntityManager em = emf.createEntityManager();

        String jptl = "Insert Into Comment(ArticleID, Email, Content, Date) Values(?, ?, ?, ?)";
        Query query = em.createNativeQuery(jptl);
        em.getTransaction().begin();
        query.setParameter("1", cmt.getArticleID().getArticleID());
        query.setParameter("2", cmt.getEmail().getEmail());
        query.setParameter("3", cmt.getContent());
        query.setParameter("4", cmt.getDate());
        boolean check = query.executeUpdate() > 0;
        em.getTransaction().commit();

        return check;
    }

    public List getAllComments(Article article) {
        EntityManager em = emf.createEntityManager();
        String jptl = "Select cmt From Comment cmt Where cmt.articleID.articleID = :articleID Order by cmt.date";
        Query query = em.createQuery(jptl);
        query.setParameter("articleID", article.getArticleID());
        return query.getResultList();
    }

    public boolean deleteAllComments(Article article) {
        EntityManager em = emf.createEntityManager();
        String jptl = "Delete From Comment comment Where comment.articleID.articleID = :articleID";
        Query query = em.createQuery(jptl);
        em.getTransaction().begin();
        query.setParameter("articleID", article.getArticleID());
        boolean check = query.executeUpdate() >= 0;
        em.getTransaction().commit();
        return check;
    }
    
    public boolean deleteComment(int commentID){
        EntityManager em = emf.createEntityManager();
        String jptl = "Delete From Comment comment Where comment.commentID = :commentID";
        Query query = em.createQuery(jptl);
        em.getTransaction().begin();
        query.setParameter("commentID", commentID);
        boolean check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }
}
