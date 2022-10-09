package com.demo.suvidha.repo;

import org.springframework.data.repository.CrudRepository;

import com.demo.suvidha.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String>{

}
