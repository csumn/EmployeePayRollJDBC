import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.EmployeePayRollService;
import com.bridgelabz.EmployeePayRollService.IOService;
import com.bridgelabz.EmployeePayrollData;
//import com.bridgelabz.JDBCConnection;

public class JDBCTest {
	
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
    
    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldMatch() {
    	EmployeePayRollService employeePayrollService = new EmployeePayRollService();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
        employeePayrollService.updateEmployeeDataUsingStatement("Terisa",550000.00);
        System.out.println("The number of employees are  "+employeePayrollData.size());
    }
}
