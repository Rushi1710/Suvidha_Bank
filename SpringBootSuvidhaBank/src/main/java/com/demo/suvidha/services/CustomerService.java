package com.demo.suvidha.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.suvidha.entity.Customer;
import com.demo.suvidha.repo.CustomerRepository;

@Service
public class CustomerService 
{

	@Autowired
	private CustomerRepository customerRepository;

	public Customer findCustomerById(String customerIdentity) {

		Optional<Customer> optc = this.customerRepository.findById(customerIdentity);
		return optc.orElseThrow(() -> new EntityNotFoundException("Customer Not Found"));
	}

	public boolean insertCustomer(Customer customer) {
		if (!this.customerRepository.existsById(customer.getCustomerIdentity())) {
			this.customerRepository.save(customer);
			return true;
		}
		return false;
	}
	
}
