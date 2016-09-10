package DataAccess.Entity;

import DataAccess.Entity.Transaction;
import DataAccess.Entity.User;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2016-09-10T15:55:51")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile CollectionAttribute<Account, Transaction> transactionCollection;
    public static volatile SingularAttribute<Account, BigInteger> balance;
    public static volatile SingularAttribute<Account, User> userdocument;
    public static volatile SingularAttribute<Account, Integer> accountNumber;

}