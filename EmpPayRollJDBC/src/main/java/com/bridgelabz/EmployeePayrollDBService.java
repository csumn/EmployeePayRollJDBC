package com.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {

	private String url = "jdbc:mysql://localhost:3306/EmpPayRollService?useSSL=false";
	private String username = "root";
	private String password = "Rootpassword";
	Connection connection;

	public List<EmployeePayrollData> readData() {
		String sqlQuery = "Select * from employee_payroll";
		List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
		try {
			Connection connection = this.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double basic_pay = resultSet.getDouble("basic_pay");
				LocalDate startDate = resultSet.getDate("start").toLocalDate();
				employeePayrollList.add(new EmployeePayrollData(id,name,basic_pay,startDate));
			}
			connection.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		return employeePayrollList;
	}

	private Connection getConnection() {
		try {
			connection = DriverManager.getConnection(url, username, password);
			if (connection != null) {
				System.out.println("Connected to Database ");
			}
		} catch (SQLException e) {
			System.out.println("Exception occured while connecting data base : " +e);
		}
		return connection;
	}

	public int updateEmployeeDataUsingStatement(String name, double basic_pay) {
		String sqlQuery = String.format("update employee_payroll set basic_pay = %2f where name = '%s';",basic_pay, name);
		try(Connection connection = this.getConnection()){
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sqlQuery);
		}catch (Exception e) {
			System.out.println("Exception occured while updating salary : "+e);
		}
		return 0;
	}

	public ResultSet retrieveAccordingToDate(String date_string) {
		String sqlQuery = String.format("select * from employee_payroll where start BETWEEN CAST('%s' AS DATE) AND DATE(NOW());",date_string);
		try(Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			System.out.println("\nData of employee's who joined between "+date_string+" and now are as follows\n");
			while (resultSet.next())
			{
				System.out.println(resultSet.getString(1)+"		"+
						resultSet.getString(2)+"		"+
						resultSet.getString(3)+"		"+
						resultSet.getString(4)+"		"+
						resultSet.getString(5)+"      	"+
						resultSet.getString(6)+"		"+
						resultSet.getString(7));
			}
			System.out.println("\nEnd of data\n");
			connection.close();
		}
		catch (SQLException e)
		{
			System.out.println("Exception occured while executing query on date : "+e );
		}
		return null;
	}

	public ResultSet sumUsingGroupByGender(String value) {
		String sqlQuery = String.format("Select gender,sum(%s) from employee_payroll group by gender;",value);
		try(Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			System.out.println("\nSum of Salary of the employee's group by Gender is as follows \n");
			while (resultSet.next())
			{
				System.out.println(resultSet.getString(1)+" "+
						resultSet.getString(2));
			}
			System.out.println("\nEnd of data\n");
			connection.close();
		}
		catch (SQLException e)
		{
			System.out.println("Exception occured while executing sumUsingGroupByGender : "+e );
		}
		return null;
	}

	public ResultSet avgUsingGroupByGender(String value) {
		String sqlQuery = String.format("Select gender,avg(%s) from employee_payroll group by gender;",value);
		try(Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			System.out.println("\nAverage of Salary of the employee's group by Gender is as follows \n");
			while (resultSet.next())
			{
				System.out.println(resultSet.getString(1)+" "+
						resultSet.getString(2));
			}
			System.out.println("\nEnd of data\n");
			connection.close();
		}
		catch (SQLException e)
		{
			System.out.println("Exception occured while executing avgUsingGroupByGender : "+e );
		}
		return null;
	}

	public ResultSet minSalaryUsingGroupByGender(String value) {
		String sqlQuery = String.format("Select gender,min(%s) from employee_payroll group by gender;",value);
		try(Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			System.out.println("\nMin Salary of the employee grouped by Gender is : \n");
			while (resultSet.next())
			{
				System.out.println(resultSet.getString(1)+" "+
						resultSet.getString(2));
			}
			System.out.println("\nEnd of data\n");
			connection.close();
		}
		catch (SQLException e)
		{
			System.out.println("Exception occured while executing minSalaryUsingGroupByGender : "+e );
		}
		return null;
	}

	public ResultSet maxSalaryUsingGroupByGender(String value) {
		String sqlQuery = String.format("Select gender,max(%s) from employee_payroll group by gender;",value);
		try(Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			System.out.println("\nMax Salary of the employee grouped by Gender is : \n");
			while (resultSet.next())
			{
				System.out.println(resultSet.getString(1)+" "+
						resultSet.getString(2));
			}
			System.out.println("\nEnd of data\n");
			connection.close();
		}
		catch (SQLException e)
		{
			System.out.println("Exception occured while executing minSalaryUsingGroupByGender : "+e );
		}
		return null;
	}

	public ResultSet countEmployeesUsingGroupByGender(String value) {
		String sqlQuery = String.format("Select gender,count(%s) from employee_payroll group by gender;",value);
		try(Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			System.out.println("\nNumber of employees grouped by Gender are : \n");
			while (resultSet.next())
			{
				System.out.println(resultSet.getString(1)+" "+
						resultSet.getString(2));
			}
			System.out.println("\nEnd of data\n");
			connection.close();
		}
		catch (SQLException e)
		{
			System.out.println("Exception occured while executing minSalaryUsingGroupByGender : "+e );
		}
		return null;
	}

	public void addNewEmployees(String id,String name, char gender,double basic_pay, String start, String department,double deductions,double taxable_pay,double tax,double net_pay) {
		String sqlQuery = String.format("insert into employee_payroll(id,name,gender,basic_pay,start,department,deductions,taxable_pay,tax,net_pay)values('%s','%s','%s',%2f,CAST('%s' AS DATE),'%s',%2f,%2f,%2f,%2f)", id,name, gender, basic_pay, start, department,deductions,taxable_pay,tax,net_pay);
		try(Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sqlQuery);

			String sqlQuery2 ="select * from employee_payroll;";
			try(Connection connection2 = this.getConnection()) {
				Statement statement2 = connection.createStatement();
				ResultSet resultSet = statement2.executeQuery(sqlQuery2);
				System.out.println("\nNew Employee Added to table  : \n");
				while (resultSet.next())
				{
					System.out.println(resultSet.getString(1)+" "+
							resultSet.getString(2)+" 	"+
							resultSet.getString(3)+" 	"+
							resultSet.getString(4)+"	"+
							resultSet.getString(5)+"	"+
							resultSet.getString(6)+"	"+
							resultSet.getString(7)+"	"+
							resultSet.getString(8)+"	"+
							resultSet.getString(9)+"	"+
							resultSet.getString(10));
				}
				System.out.println("\nEnd of data\n");
				connection.close();
			}
			catch (SQLException e)
			{
				System.out.println("Exception occured while executing minSalaryUsingGroupByGender : "+e );
			}
		}
		catch (SQLException e)
		{
			System.out.println("Exception occured while addNewEmployees : "+e );
		}
	}

	public void updateDataUsingPreparedStatement(String name,int id) {

		String sqlQuery1 = "update employee_payroll set name = ? where id = ?";
		String sqlQuery2 = "update employee_payroll set basic_pay = ? where name = ?";

		try (Connection conn = this.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery1);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);

			int rowAffected = preparedStatement.executeUpdate();
			System.out.println(String.format("Name updated using Prepared Statment -- Row affected %d\n", rowAffected));

			PreparedStatement preparedStatement2 = conn.prepareStatement(sqlQuery2);
			name = "DK";
			Double basic_pay = 6500000.00;

			preparedStatement2.setDouble(1, basic_pay);
			preparedStatement2.setString(2, name);

			rowAffected = preparedStatement2.executeUpdate();

			name = "Kelvin";
			basic_pay = 2200000.00;

			preparedStatement2.setDouble(1, basic_pay);
			preparedStatement2.setString(2, name);

			rowAffected = preparedStatement2.executeUpdate();
			System.out.println(String.format("Salary updated using Prepared Statment -- Row affected %d", rowAffected));

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteEmployeePayrollER(String name) throws SQLException {
		String sql = String.format("delete from employee_payroll where name='%s';",name);
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			String sqlQuery="SELECT * FROM employee_payroll;";
			try {
				Connection connection2 = this.getConnection();
				Statement statement2 = connection2.createStatement();
				ResultSet resultSet1 = statement2.executeQuery(sqlQuery);
				while (resultSet1.next()){
					System.out.println(
							resultSet1.getString(1)+" "+
									resultSet1.getString(2)+" "+
									resultSet1.getString(3)+" "+
									resultSet1.getString(4)+" "+
									resultSet1.getString(5)+" "+
									resultSet1.getString(6));
				}

			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void addEmployeeToPayroll(String name, char gender, double basic_pay, String start) throws SQLException {
		int payment_id = 99;
		Connection connection = null;
		connection = this.getConnection();
		connection.setAutoCommit(false);
		Statement statement = connection.createStatement();
		try {
			String sql = String.format("insert into employee_payroll(name,gender,basic_pay,start) values" +"('%s','%s',%2f,CAST('%s' AS DATE))", name, gender, basic_pay, start);
			int rowAffected = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if (rowAffected == 1) {
				System.out.println(payment_id);
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) payment_id = resultSet.getInt(1);
				System.out.println(payment_id);
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
			connection.rollback();
		}
		try {
			double deductions = basic_pay * 0.2;
			double taxable_Pay = basic_pay - deductions;
			double tax = taxable_Pay * 0.1;
			double netPay = basic_pay - tax;
			String sql = String.format("insert into payroll" +
					"(payment_id,basic_pay,deductions,taxable_Pay,tax,net_pay) values"+"(%s,%s,%s,%s,%s,%s)",payment_id, basic_pay,deductions,taxable_Pay, tax, netPay);
			@SuppressWarnings("unused")
			int rowAffected = statement.executeUpdate(sql);
		}
		catch (SQLException e){
			System.out.println("inside catch");
			e.printStackTrace();
			connection.rollback();
		}
		try {
			connection.commit();
			String sql="select * from payroll;";
			try {
				Connection connection2 = this.getConnection();
				Statement statement2 = connection2.createStatement();
				ResultSet resultSet2 = statement2.executeQuery(sql);
				while (resultSet2.next()){
					System.out.println(
							resultSet2.getString(1)+" 	"+
									resultSet2.getString(2)+"	 "+
									resultSet2.getString(3)+"	 "+
									resultSet2.getString(4)+"	 "+
									resultSet2.getString(5));
				}

			}
			catch (SQLException e)
			{
				System.out.println("Exception inside payroll display data "+e);
			}		}
		catch (SQLException e)
		{
			System.out.println("Exception inside AddToPayroll display data "+e);
		}
	}
}

