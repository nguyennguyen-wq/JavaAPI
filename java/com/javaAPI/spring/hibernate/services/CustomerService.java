package com.javaAPI.spring.hibernate.services;
import java.util.List;
import com.javaAPI.spring.hibernate.model.Customer;
import com.javaAPI.spring.hibernate.repository.CustomerRepository;

public class CustomerService {

    private final CustomerRepository repo;
    public CustomerService(CustomerRepository repo)
    {
        this.repo = repo;
    }

    public List<Customer> getAllCustomers()
    {
        return repo.findAll();
    }

    public Customer createCustomer(Customer customer)
    {
        repo.save(customer);
        return customer;
    }

}
