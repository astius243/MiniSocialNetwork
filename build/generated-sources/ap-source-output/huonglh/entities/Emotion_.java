package huonglh.entities;

import huonglh.entities.Account;
import huonglh.entities.Article;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-01T21:19:32")
@StaticMetamodel(Emotion.class)
public class Emotion_ { 

    public static volatile SingularAttribute<Emotion, String> emotion;
    public static volatile SingularAttribute<Emotion, Integer> emotionID;
    public static volatile SingularAttribute<Emotion, Article> articleID;
    public static volatile SingularAttribute<Emotion, Account> email;

}