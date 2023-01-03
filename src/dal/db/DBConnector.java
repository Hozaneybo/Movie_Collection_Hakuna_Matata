package dal.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class DBConnector {
    private SQLServerDataSource dataSource;

    public DBConnector(){

        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("Movie_Collection");
        dataSource.setUser("CSe22A_12"); // Type your given username
        dataSource.setPassword("Hamada007"); // Type your given password
        dataSource.setTrustServerCertificate(true);
        dataSource.setPortNumber(1433); // From school
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
}
