package com.demo.suvidha.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.suvidha.entity.Customer;
import com.demo.suvidha.entity.Employee;
import com.demo.suvidha.entity.Loan;
import com.demo.suvidha.repo.EmployeeRepository;
import com.demo.suvidha.repo.LoanRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private LoanRepository loanRepository;

	public boolean searchByEmployeeId(String employeeId) {
		if (this.employeeRepository.existsById(employeeId))
			return true;
		return false;
	}

	public boolean addEmployee(Employee employee) {
		if (!this.employeeRepository.existsById(employee.getEmpId())) {
			this.employeeRepository.save(employee);
			return true;
		}
		return false;
	}

	public void approveLoan(String empId) throws Exception {
		if (this.searchByEmployeeId(empId)) 
		{			
			
			List<Loan> loans=this.loanRepository.searchEmply(empId);
			System.out.println(loans);
			if(loans.size()>0) {
				for(Loan loan:loans) {
					Customer customer=loan.getCustomer();
					if(loan.getLoanAmount() > (10 * customer.getAnnuallncome())) {
						loan.setRemarks("Loan amount cannot be 10 times of annual income");
						loan.setApproved(false);
						this.loanRepository.save(loan);
						
					}
					
					else if(!customer.isIncomeTaxReturnAttached()) {
						loan.setRemarks( "Income proof not attached");
						loan.setApproved(false);
						this.loanRepository.save(loan);
						
					}
					else if(customer.getCustomerIdentity() == null) {
						loan.setRemarks( "Identity document not submitted");
						loan.setApproved(false);
						this.loanRepository.save(loan);
						
					}
					else if(loan.getCollaterals() == null || loan.getCollaterals().size() == 0) {
						loan.setRemarks( "No collateral submitted");
					   loan.setApproved(false);
						this.loanRepository.save(loan);
						
					}
					else
					{
						loan.setRemarks( "Approved");
						loan.setApproved(true);
						
						this.loanRepository.save(loan);
						
					}
					System.out.println(loans);
				}
			}
			else {
				throw new Exception("No loan proposals against this employee id");
			}
		}
		else
			throw new EntityNotFoundException("Employee does not exist");
				}
}
