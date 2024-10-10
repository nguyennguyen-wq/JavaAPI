package com.javaAPI.spring.hibernate.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.javaAPI.spring.hibernate.model.Customer;
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
