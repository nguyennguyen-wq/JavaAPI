package com.javaAPI.spring.hibernate.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.javaAPI.spring.hibernate.exception.ResourceNotFoundException;
import com.javaAPI.spring.hibernate.model.Customer;
import com.javaAPI.spring.hibernate.repository.CustomerRepository;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CustomerController {
  @Autowired
  CustomerRepository customerRepository;
 
  @GetMapping("/customers")
  public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(required = false) String lastname) {
    List<Customer> customers = new ArrayList<Customer>();
    if (lastname == null)
      customerRepository.findAll().forEach(customers::add);
    if (customers.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(customers, HttpStatus.OK);
  }


  @GetMapping("/customers/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
    Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Customer" +
                " with id = " + id));
    return new ResponseEntity<>(customer, HttpStatus.OK);
  }

  @PostMapping("/customers")
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    Customer _customer = customerRepository.save(new Customer(customer.getFirstname(), customer.getLastname(),customer.getSocialsecuritynumber()));
    return new ResponseEntity<>(_customer, HttpStatus.CREATED);
  }

  @PutMapping("/customers/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customerDetails) {
    Optional<Customer> customerData = customerRepository.findById(id);
    if (customerData.isPresent()) {
      Customer customer = customerData.get();
      customer.setFirstname(customerDetails.getFirstname());
      customer.setLastname(customerDetails.getLastname());
      customer.setSocialsecuritynumber(customerDetails.getSocialsecuritynumber());
      Customer updatedCustomer = customerRepository.save(customer);
      return new ResponseEntity<>(updatedCustomer, HttpStatus.OK); 
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }
  }

  @DeleteMapping("/customers/{id}")
  public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id) {
    customerRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/customers")
  public ResponseEntity<HttpStatus> deleteAllCustomers() {
    customerRepository.deleteAll();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
