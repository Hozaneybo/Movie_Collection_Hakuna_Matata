package dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

/**
 * DBConnector class is responsible for creating a connection to the database using
 * SQL Server DataSource.
 */
public class DBConnector {
    private SQLServerDataSource dataSource;

    /**
     * Constructor method that initializes the SQLServerDataSource object and sets the
     * server name, database name, username, password, trustServerCertificate, and port number.
     */
    public DBConnector(){

        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("Movie_Collection");
        dataSource.setUser("CSe22A_23"); // Type your given username
        dataSource.setPassword("****"); // Type your given password
        dataSource.setTrustServerCertificate(true);
        dataSource.setPortNumber(1433); // From school
    }

    /**
     * Method that returns a connection to the database using the SQLServerDataSource object.
     * @return Connection object
     * @throws SQLServerException
     */
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
}
