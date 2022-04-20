package com.bridgelabz;

import java.time.LocalDate;

public class EmployeePayrollData {

	public int id;
	public String name;
	public double basic_pay;

	public EmployeePayrollData(int id, String name, double basic_pay, LocalDate startDate) {
		this.id = id;
		this.name = name;
		this.basic_pay = basic_pay;
	}

	@Override
	public String toString() {
		return "EmployeePayrollData [id=" + id + ", name=" + name + ", basic_pay=" + basic_pay + "]";
	}
}
