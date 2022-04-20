import java.sql.Connection;

import org.junit.Test;

import com.bridgelabz.JDBCConnection;

public class JDBCDemo {
	
    @Test
    public void getDB_Connection() {
        Connection dbConnection = new JDBCConnection().getDBConnection();
        System.out.println(dbConnection);
    }
}
