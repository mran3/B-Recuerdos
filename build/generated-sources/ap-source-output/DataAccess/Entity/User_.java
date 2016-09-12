package DataAccess.Entity;

import DataAccess.Entity.Employee;
import DataAccess.Entity.Order;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2016-09-11T19:19:05")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile CollectionAttribute<User, Employee> employeesCollection;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile CollectionAttribute<User, Order> ordersCollection;
    public static volatile SingularAttribute<User, String> email;

}