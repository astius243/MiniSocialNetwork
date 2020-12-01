package huonglh.entities;

import huonglh.entities.Account;
import huonglh.entities.Article;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-01T21:19:32")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, Date> date;
    public static volatile SingularAttribute<Comment, Article> articleID;
    public static volatile SingularAttribute<Comment, Integer> commentID;
    public static volatile SingularAttribute<Comment, String> content;
    public static volatile SingularAttribute<Comment, Account> email;

}