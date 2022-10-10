package com.demo.suvidha.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.suvidha.entity.Customer;
import com.demo.suvidha.entity.Employee;
import com.demo.suvidha.entity.Loan;
import com.demo.suvidha.entity.collaterals;
import com.demo.suvidha.repo.CollateralRepository;
import com.demo.suvidha.repo.CustomerRepository;
import com.demo.suvidha.repo.EmployeeRepository;
import com.demo.suvidha.repo.LoanRepository;

@Service
public class LoanService 
{

	
	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CollateralRepository collateralRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public Loan findLoanById(String loanId) {
		Optional<Loan> optl = this.loanRepository.findById(loanId);
		return optl.orElseThrow(() -> new EntityNotFoundException("Loan with specified id not found"));
	}

	public static int generate(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	public List<Loan> getLoanByEmployeeId(String empId) {
		return this.loanRepository.searchEmply(empId);
	}

	public Employee getEmployeeById(String empId) {
		return this.employeeRepository.findById(empId).get();
	}
	
	public boolean updateLoan(String customerId) {
		
			Customer c=this.customerRepository.findById(customerId).get();			
			String loanid=c.getLoan().getLoanId();
			Loan l=this.loanRepository.findById(loanid).get();
			l.setRemarks("Approved");
			this.loanRepository.save(l);
			return true;
		
//		throw new EntityNotFoundException("From updateLoan Method :Customer with that id not found");
	}
	
	public Loan applyForLoan(String loanType, double loanAmount, double period, String customerIdentity) throws Exception {
		Loan l = new Loan();
		System.out.println("Asif");
//		l.setCustomer(this.customerRepository.findById(customerIdentity).get());
		l.setInterestRate(300000.0);
		l.setLoanAmount(loanAmount);
		int i=generate(10, 1000);
		l.setLoanId(i+ "");
//		l.setCollaterals(null);
		l.setLoanType(loanType);
		l.setPeriod(period);
		String s=Integer.valueOf(generate(111, 119)).toString();
		System.out.println("--------------");
		l.setEmployee(getEmployeeById(s));
		l.setLoanType(loanType);
		l.setLoanAmount(loanAmount);
		l.setPeriod(period);
		l.setCustomer(this.customerRepository.findById(customerIdentity).get());
		try {
		this.loanRepository.save(l);
		System.out.println("Done");
		}
		catch(Exception e) {
			
		}
		System.out.println("Anwarul");
		System.out.println(l.getInterestRate());
		this.employeeService.approveLoan(s);
		return l;

	}

	public boolean uploadCollateral(String loanId, List<collaterals> collaterals) {

		Loan l = this.loanRepository.findById(loanId).get();
		if (collaterals.size() > 0) {
			for (collaterals c : collaterals) {
//				c.setLoan(l);
//				l.setCollaterals(collaterals);
			}
			return true;
		}
		return false;
	}

	
	
	
}
