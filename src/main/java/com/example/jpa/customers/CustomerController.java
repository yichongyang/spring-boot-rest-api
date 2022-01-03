package com.example.jpa.customers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CustomerController {
    @Autowired CustomerService customerService;
   
    @GetMapping(path = "/customers")
    public List<Customer> getCustomer() {
        return customerService.findAll();
    }
    
    @PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
    	return customerService.addCustomer(customer);
    }
  
    // Request parameter for query with format as /customer?id=xxx
	@GetMapping("/customer")
    public Customer getCustomerById(@RequestParam long id) {
        return customerService.findById(id);
    }

    // Path parameter for getting
    @GetMapping(path = "/customer/id/{id}")
    public Customer getById(@PathVariable long id) {
        return customerService.findById(id);
    }

	@GetMapping(path = "/customer/lastName/{lastName}")
    public List<Customer> getByLastName(@PathVariable String lastName) {
        return customerService.findByLastName(lastName);
    }
    
    @DeleteMapping(path = "/customer/delete/id/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
    	Customer customer = customerService.findById(id);
    	customerService.deleteCustomer(id);
    	return ResponseEntity.status(HttpStatus.OK).body(customer.getFirstName() + " " + customer.getLastName() + " was deleted");
    }
}
