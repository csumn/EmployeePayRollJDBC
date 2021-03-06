import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.EmployeePayRollService;
import com.bridgelabz.EmployeePayrollDBService;
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
		System.out.println("\n***Data from the table is as follows ***\n");
		System.out.println(employeePayrollData.toString());
		Assert.assertEquals(14,employeePayrollData.size());
	}

	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldMatch() {
		EmployeePayRollService employeePayRollService = new EmployeePayRollService();
		@SuppressWarnings("unused")
		List<EmployeePayrollData> employeePayrollData = employeePayRollService.readEmployeePayrollData(IOService.DB_IO);
		employeePayRollService.updateEmployeeDataUsingStatement("Terisa",550000.00);
	}

	@Test
	public void givenStartDate_RetriveAllTheEmployeesWhoJoined_InThatDateRange() {
		EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
		String date_string = "2018-01-02";
		employeePayrollDBService.retrieveAccordingToDate(date_string);
	}

	@Test
	public void abilityToFindSumOfSalary_GroupByGender() {
		EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
		employeePayrollDBService.sumUsingGroupByGender("basic_pay");
	}

	@Test
	public void abilityToFindAvgOfSalary_GroupByGender() {
		EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
		employeePayrollDBService.avgUsingGroupByGender("basic_pay");
	}

	@Test
	public void abilityToFindMinSalary_GroupByGender() {
		EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
		employeePayrollDBService.minSalaryUsingGroupByGender("basic_pay");
	}

	@Test
	public void abilityToFindMaxSalary_GroupByGender() {
		EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
		employeePayrollDBService.maxSalaryUsingGroupByGender("basic_pay");
	}

	@Test
	public void abilityToCountEmployeesUsingGroupByGender() {
		EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
		employeePayrollDBService.countEmployeesUsingGroupByGender("gender");
	}

	@Test
	public void abilityToAddNewEmployee() {
		EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
		employeePayrollDBService.addNewEmployees("14","Smith", 'M', 4000000.0, "2021-06-12","Sales",50000.0,12000.0,200000.00,3500000.00);
	}

	@Test
	public void givenNewSalaryAndOtherDetailsForEmployee_WhenUpdated_ShouldMatch2() {
		EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
		employeePayrollDBService.updateDataUsingPreparedStatement("Jack", 7);
	}

	@Test
	public void abilityToAddNewEmployeeAndPayRoll() throws SQLException {
		EmployeePayrollDBService employeePayrollDBService = new EmployeePayrollDBService();
		employeePayrollDBService.addEmployeeToPayroll("Sams", 'F', 9000000.00, "2017-08-12");
	}

	@Test
	public void abilityToDeleteEmployeeInPayroll() throws SQLException {
		EmployeePayrollDBService employeePayrollDBService=new EmployeePayrollDBService();
		employeePayrollDBService.deleteEmployeePayrollER("Sams");
	}
}


