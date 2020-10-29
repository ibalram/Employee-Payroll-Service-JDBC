package com.cg.employeejdbc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.employeejdbc.model.EmployeePayrollData;

public class EmployeePayrollService {
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO;
	}

	private List<EmployeePayrollData> employeePayrollList;

	public EmployeePayrollService() {

	}

	public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
		this.employeePayrollList = employeePayrollList;
	}

	public static void main(String[] args) {
		ArrayList<EmployeePayrollData> employeePayrollList = new ArrayList<>();
		EmployeePayrollService empService = new EmployeePayrollService(employeePayrollList);
		Scanner sc = new Scanner(System.in);
		empService.readEmployeePayrollData(sc);
		empService.writeEmployeePayrollData(IOService.CONSOLE_IO);
	}

	private void readEmployeePayrollData(Scanner sc) {
		System.out.println("Enter Employee ID: ");
		int id = Integer.parseInt(sc.nextLine());
		System.out.println("Enter Employee name: ");
		String name = sc.nextLine();
		System.out.println("Enter Employee Salary: ");
		Double salary = Double.parseDouble(sc.nextLine());
		employeePayrollList.add(new EmployeePayrollData(id, name, salary));
	}

	public void writeEmployeePayrollData(IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO))
			System.out.println("Writing Employee Payroll Data to Console\n" + employeePayrollList);
		else if (ioService.equals(IOService.FILE_IO))
			new EmployeePayrollFileIOService().writeData(employeePayrollList);
	}

	public long countEntries(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new EmployeePayrollFileIOService().countNoOfEntries();
		return 0;
	}

	public void printData(IOService ioService) {
		new EmployeePayrollFileIOService().printEmployeePayrollData();
	}

	public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new EmployeePayrollFileIOService().readData();
		else if (ioService.equals(IOService.DB_IO)) {
			this.employeePayrollList = new EmployeePayrollDBService().readData();
			return this.employeePayrollList;
		} else
			return null;
	}

}
