package DataAccess.Entity;

import DataAccess.Entity.Account;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2016-09-11T14:01:44")
@StaticMetamodel(Transaction.class)
public class Transaction_ { 

    public static volatile SingularAttribute<Transaction, Date> date;
    public static volatile SingularAttribute<Transaction, BigInteger> amount;
    public static volatile SingularAttribute<Transaction, Integer> transactionNumber;
    public static volatile SingularAttribute<Transaction, Account> accountaccountNumber;
    public static volatile SingularAttribute<Transaction, Integer> destinationAccount;

}