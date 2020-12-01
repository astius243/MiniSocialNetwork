package huonglh.entities;

import huonglh.entities.Account;
import huonglh.entities.Emotion;
import huonglh.entities.Notification;
import huonglh.entities.Status;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-01T21:19:32")
@StaticMetamodel(Article.class)
public class Article_ { 

    public static volatile SingularAttribute<Article, Date> date;
    public static volatile SingularAttribute<Article, String> image;
    public static volatile CollectionAttribute<Article, Notification> notifyCollection;
    public static volatile SingularAttribute<Article, Status> statusID;
    public static volatile SingularAttribute<Article, Integer> articleID;
    public static volatile SingularAttribute<Article, String> description;
    public static volatile CollectionAttribute<Article, Emotion> emotionCollection;
    public static volatile SingularAttribute<Article, String> title;
    public static volatile SingularAttribute<Article, Account> email;

}