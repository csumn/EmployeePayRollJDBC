package com.bridgelabz;

import java.util.List;

public class EmployeePayRollService {

	public enum IOService {
		CONSOLE_IO,FILE_IO,DB_IO,RSET_IO;
	}	

	public List<EmployeePayrollData> employeePayrollList;

	public EmployeePayRollService() {}

	public EmployeePayRollService (List<EmployeePayrollData> employeePayrollList) {
		this.employeePayrollList = employeePayrollList;
	}

	public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
		if(ioService.equals(IOService.DB_IO))
			this.employeePayrollList = new EmployeePayrollDBService().readData();
		return this.employeePayrollList;
	}

	public void updateEmployeeDataUsingStatement(String name, double basic_pay) {
		int result = new EmployeePayrollDBService().updateEmployeeDataUsingStatement(name,basic_pay);
		if(result == 0) return;
		EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
		if(employeePayrollData!=null) 
			employeePayrollData.basic_pay = basic_pay;
	}

	private EmployeePayrollData getEmployeePayrollData(String name) {
		return this.employeePayrollList.stream().filter(employeePayrollData -> employeePayrollData.name.equals(name)).findFirst().orElse(null);
	}
}
