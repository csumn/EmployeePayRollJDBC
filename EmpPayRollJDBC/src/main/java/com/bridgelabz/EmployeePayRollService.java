package com.bridgelabz;

import java.util.List;

public class EmployeePayRollService {

	public enum IOService {
		CONSOLE_IO,FILE_IO,DB_IO,RSET_IO;
	}	
	
	public List<EmployeePayrollData> employeePayrollList;

	public EmployeePayRollService() {}

	public EmployeePayRollService (List<EmployeePayrollData> employeePayrollList) {
		this.employeePayrollList=employeePayrollList;
	}

	public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
		if(ioService.equals(IOService.DB_IO))
			this.employeePayrollList = new EmployeePayrollDBService().readData();
		return this.employeePayrollList;
	}
}
