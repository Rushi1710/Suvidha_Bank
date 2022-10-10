package com.demo.suvidha;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.suvidha.entity.Customer;
import com.demo.suvidha.entity.Employee;
import com.demo.suvidha.entity.Loan;
import com.demo.suvidha.entity.collaterals;
import com.demo.suvidha.repo.LoanRepository;
import com.demo.suvidha.services.EmployeeService;
//This is main class
@SpringBootApplication
public class SpringBootSuvidhaBankApplication {

	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSuvidhaBankApplication.class, args);
	}
	
//	@Bean
	public void initialize() {
		Customer c1=new Customer();
		c1.setCustomerIdentity("532");
		c1.setCustomerName("Rushikesh");
		c1.setCustomerAdd("Kalamboli");
		c1.setCustomerEmail("Rushi@123");
		c1.setAnnuallncome(120000.0);
		c1.setIncomeTaxReturnAttached(true);
		
		Loan l1=new Loan();
		l1.setLoanId("2001");
		l1.setLoanType("Buying Car");
		l1.setLoanAmount(150000.0);
		l1.setInterestRate(3000.0);
		l1.setPeriod(3.0);
		l1.setRemarks("Well Cooperated");
		l1.setCustomer(c1);
		
		
		
		
		for(int i=110;i<120;i++) {
			Employee e=new Employee();
			e.setEmpId(""+i);
			e.setEmpName("Employee"+(i-100));
			this.employeeService.addEmployee(e);
		}
//	
	
  	
     	this.loanRepository.save(l1);
	}
	

}
