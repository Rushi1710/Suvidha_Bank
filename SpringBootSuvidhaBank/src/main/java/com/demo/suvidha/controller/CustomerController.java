package com.demo.suvidha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.suvidha.dto.Loandto;
import com.demo.suvidha.entity.Customer;
import com.demo.suvidha.entity.Loan;
import com.demo.suvidha.entity.collaterals;
import com.demo.suvidha.repo.CollateralRepository;
import com.demo.suvidha.repo.EmployeeRepository;
import com.demo.suvidha.services.CustomerService;
import com.demo.suvidha.services.LoanService;

@RestController
public class CustomerController 
{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CollateralRepository collateralRepository;
	
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/customers")
	public String home() {
		return "home";
	}
	
	@PostMapping("/customer")
	public String addCustomer(@RequestBody Customer customer) {
		System.out.println(customer);
		return this.customerService.insertCustomer(customer);
	}
	
	
	@GetMapping("/customer/{id}")
	public Customer getCustomerById(@PathVariable String id) {
		System.out.println(id);
	return this.customerService.findCustomerById(id);
	}
	
	@GetMapping("/loan/{id}")
	public Loan getCustometLoanById(@PathVariable String id) {
	return this.loanService.findLoanById(id);
	}
	

	@PostMapping("/loan")
	public Loan applyForLoan(@RequestBody Loandto loandto) throws Exception 
	{	
		
		System.out.println(loandto);
		return this.loanService.applyForLoan(loandto.getType(),loandto.getAmount(),loandto.getPeriod(),loandto.getIdentity());

	}
	@PostMapping("/collat/{cid}")
	public boolean addCollat(@RequestBody collaterals collateral,@PathVariable String cid) {
		this.collateralRepository.save(collateral);
		return this.loanService.updateLoan(cid);
		
	}
}
