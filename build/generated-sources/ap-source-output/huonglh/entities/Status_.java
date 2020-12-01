package huonglh.entities;

import huonglh.entities.Account;
import huonglh.entities.Article;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-01T21:19:32")
@StaticMetamodel(Status.class)
public class Status_ { 

    public static volatile CollectionAttribute<Status, Article> articleCollection;
    public static volatile CollectionAttribute<Status, Account> accountCollection;
    public static volatile SingularAttribute<Status, Integer> statusID;
    public static volatile SingularAttribute<Status, String> name;

}