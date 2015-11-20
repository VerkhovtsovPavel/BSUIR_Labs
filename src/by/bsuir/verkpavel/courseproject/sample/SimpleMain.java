package by.bsuir.verkpavel.courseproject.sample;

import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Main sample routine to show how to do basic operations with the package.
 * 
 * <p>
 * <b>NOTE:</b> We use asserts in a couple of places to verify the results but if this were actual production code, we
 * would have proper error handling.
 * </p>
 */
public class SimpleMain {

    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bank_users?useUnicode=true&characterEncoding=utf8";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "123456";

    private Dao<Currency, Integer> currencyDao;

    public static void main(String[] args) throws Exception {
        // turn our static method into an instance of Main
        new SimpleMain().doMain(args);
    }

    private void doMain(String[] args) throws Exception {
        ConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(DATABASE_URL, DB_USER_NAME, DB_PASSWORD);
            currencyDao = DaoManager.createDao(connectionSource, Currency.class);
            List<Currency> currencies = currencyDao.queryForAll();
           for(Currency currency : currencies) {
               System.out.println(currency.getName());
           }
        } finally {

            if (connectionSource != null) {
                connectionSource.close();
            }
        }
    }


//    /**
//     * Read and write some example data.
//     */
//    private void readWriteData() throws Exception {
//        // create an instance of Account
//        String name = "Jim Coakley";
//        Currency account = new Currency(name);
//
//        // persist the account object to the database
//        currencyDao.create(account);
//        int id = account.getId();
//        verifyDb(id, account);
//
//        // assign a password
//        // update the database after changing the object
//        currencyDao.update(account);
//        verifyDb(id, account);
//
//        // query for all items in the database
//        
//
//        // construct a query using the QueryBuilder
//        QueryBuilder<Currency, Integer> statementBuilder = currencyDao.queryBuilder();
//        // shouldn't find anything: name LIKE 'hello" does not match our account
//        statementBuilder.where().like(Currency.CURRENCY_FIELD_NAME, "hello");
//        accounts = currencyDao.query(statementBuilder.prepare());
//
//        // should find our account: name LIKE 'Jim%' should match our account
//        statementBuilder.where().like(Currency.CURRENCY_FIELD_NAME, name.substring(0, 3) + "%");
//        accounts = currencyDao.query(statementBuilder.prepare());
//
//        // delete the account since we are done with it
//        currencyDao.delete(account);
//        // we shouldn't find it now
//    }
//
//    /**
//     * Example of reading and writing a large(r) number of objects.
//     */
//    private void readWriteBunch() throws Exception {
//
//        Map<String, Currency> accounts = new HashMap<String, Currency>();
//        for (int i = 1; i <= 100; i++) {
//            String name = Integer.toString(i);
//            Currency account = new Currency(name);
//            // persist the account object to the database, it should return 1
//            currencyDao.create(account);
//            accounts.put(name, account);
//        }
//
//        // query for all items in the database
//        List<Currency> all = currencyDao.queryForAll();
//    }
//
//    /**
//     * Example of created a query with a ? argument using the {@link SelectArg} object. You then can set the value of
//     * this object at a later time.
//     */
//    private void useSelectArgFeature() throws Exception {
//
//        String name1 = "foo";
//        String name2 = "bar";
//        String name3 = "baz";
//
//        QueryBuilder<Currency, Integer> statementBuilder = currencyDao.queryBuilder();
//        SelectArg selectArg = new SelectArg();
//        // build a query with the WHERE clause set to 'name = ?'
//        statementBuilder.where().like(Currency.CURRENCY_FIELD_NAME, selectArg);
//        PreparedQuery<Currency> preparedQuery = statementBuilder.prepare();
//
//        // now we can set the select arg (?) and run the query
//        selectArg.setValue(name1);
//        List<Currency> results = currencyDao.query(preparedQuery);
//
//        selectArg.setValue(name2);
//        results = currencyDao.query(preparedQuery);
//
//        selectArg.setValue(name3);
//        results = currencyDao.query(preparedQuery);
//    }
//
//    /**
//     * Example of created a query with a ? argument using the {@link SelectArg} object. You then can set the value of
//     * this object at a later time.
//     */
//    private void useTransactions(ConnectionSource connectionSource) throws Exception {
//        String name = "trans1";
//        final Currency account = new Currency(name);
//
//        TransactionManager transactionManager = new TransactionManager(connectionSource);
//        try {
//            // try something in a transaction
//            transactionManager.callInTransaction(new Callable<Void>() {
//                public Void call() throws Exception {
//                    // we do the delete
//                    currencyDao.delete(account);
//                    currencyDao.queryForId(account.getId());
//                    // but then (as an example) we throw an exception which rolls back the delete
//                    throw new Exception("We throw to roll back!!");
//                }
//            });
//
//        } catch (SQLException e) {
//            // expected
//        }
//
//        currencyDao.queryForId(account.getId());
//    }
//
//    /**
//     * Verify that the account stored in the database was the same as the expected object.
//     */
//    private void verifyDb(int id, Currency expected) throws SQLException, Exception {
//        // make sure we can read it back
//        Currency account2 = currencyDao.queryForId(id);
//        if (account2 == null) {
//            throw new Exception("Should have found id '" + id + "' in the database");
//        }
//    }

}
