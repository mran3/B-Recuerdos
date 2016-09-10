package DataAccess.Entity;

import DataAccess.Entity.Account;
import DataAccess.Entity.Session;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2016-09-10T15:55:51")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> lastName;
    public static volatile CollectionAttribute<User, Account> accountCollection;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Integer> document;
    public static volatile CollectionAttribute<User, Session> sessionCollection;
    public static volatile SingularAttribute<User, String> userName;

}