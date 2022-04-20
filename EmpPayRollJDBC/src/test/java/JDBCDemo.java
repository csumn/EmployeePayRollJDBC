import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.EmployeePayRollService;
import com.bridgelabz.EmployeePayRollService.IOService;
import com.bridgelabz.EmployeePayrollData;
//import com.bridgelabz.JDBCConnection;

public class JDBCDemo {
	
//    @Test
//    public void getDB_Connection() {
//        Connection dbConnection = new JDBCConnection().getDBConnection();
//        System.out.println(dbConnection);
//    }
//    
    @Test
    public void givenEmployeePayrollInDb_WhenRetrieved_ShouldMatchEmployeeCount() throws SQLException {
    	EmployeePayRollService employeePayRollService = new EmployeePayRollService();
		List<EmployeePayrollData> employeePayrollData = employeePayRollService.readEmployeePayrollData(IOService.DB_IO);
		Assert.assertEquals(4,employeePayrollData.size());
    }
}
