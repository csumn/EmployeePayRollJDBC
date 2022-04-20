package com.bridgelabz;

import java.sql.Connection;
import java.sql.DriverManager;
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
		}
		catch (SQLException e)
		{
			System.out.println("Exception occured while executing sumUsingGroupByGender : "+e );
		}
		return null;
	}
}

