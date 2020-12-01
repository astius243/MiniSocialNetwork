package huonglh.entities;

import huonglh.entities.Account;
import huonglh.entities.Article;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-01T21:19:32")
@StaticMetamodel(Notification.class)
public class Notification_ { 

    public static volatile SingularAttribute<Notification, Date> date;
    public static volatile SingularAttribute<Notification, Article> articleID;
    public static volatile SingularAttribute<Notification, Integer> notifyID;
    public static volatile SingularAttribute<Notification, String> content;
    public static volatile SingularAttribute<Notification, Account> email;

}