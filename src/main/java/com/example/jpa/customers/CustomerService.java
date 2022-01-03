package com.example.jpa.customers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@CacheEvict(value = { "customer/id", "customer/lastName", "customers" }, allEntries = true)
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Cacheable("customers")
	public List<Customer> findAll(){
        try {
            System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		return (List<Customer>) customerRepository.findAll();
	}
	
	@Cacheable("customer/lastName")
	public List<Customer> findByLastName(String lastName) {
		try {
            System.out.println("Going to sleep for 2 Secs.. to simulate backend call.");
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		return customerRepository.findByLastName(lastName);
	}
	
	@CacheEvict(value = { "customer/id", "customer/lastName", "customers" }, allEntries = true)
	public void deleteCustomer(long id) {
		Customer customer = customerRepository.findById(id);
		customerRepository.delete(customer);
	}
	
	@Cacheable("customer/id")
	public Customer findById(long id) {
		try {
            System.out.println("Going to sleep for 2 Secs.. to simulate backend call.");
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		return customerRepository.findById(id);
	}
}
