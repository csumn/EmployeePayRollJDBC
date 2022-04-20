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
				double salary = resultSet.getDouble("basic_pay");
				LocalDate startDate = resultSet.getDate("start").toLocalDate();
				employeePayrollList.add(new EmployeePayrollData(id,name,salary,startDate));
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
			System.out.println("Exception occured : " +e);
		}
		return connection;
	}
}
