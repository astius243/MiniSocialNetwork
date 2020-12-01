package huonglh.entities;

import huonglh.entities.Article;
import huonglh.entities.Notification;
import huonglh.entities.Status;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-01T21:19:32")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile CollectionAttribute<Account, Article> articleCollection;
    public static volatile CollectionAttribute<Account, Notification> notifyCollection;
    public static volatile SingularAttribute<Account, String> password;
    public static volatile SingularAttribute<Account, String> role;
    public static volatile SingularAttribute<Account, String> name;
    public static volatile SingularAttribute<Account, String> email;
    public static volatile SingularAttribute<Account, Status> status;

}