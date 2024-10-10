package com.javaAPI.spring.hibernate.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javaAPI.spring.hibernate.exception.ResourceNotFoundException;
import com.javaAPI.spring.hibernate.model.Account;
import com.javaAPI.spring.hibernate.repository.AccountRepository;
import com.javaAPI.spring.hibernate.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AccountController {
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private AccountRepository accountRepository;
  @GetMapping("/customers/{customerId}/accounts")
  public ResponseEntity<List<Account>> getAllAccountsByCustomerId(@PathVariable(value = "customerId") Long customerId) {
    if (!customerRepository.existsById(customerId)) {
      throw new ResourceNotFoundException("Not found Account with id = " + customerId);
    }
    List<Account> accounts = accountRepository.findByCustomerId(customerId);
    return new ResponseEntity<>(accounts, HttpStatus.OK);
  }

  @GetMapping("/customers/{customerId}/accounts/{id}")
  public ResponseEntity<Account> getAccountsByCustomerId(@PathVariable(value = "id") Long id) {
    Account account = accountRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Not found accounts with id = " + id));
    return new ResponseEntity<>(account, HttpStatus.OK);
  }

  @PostMapping("/customers/{customerId}/accounts")
  public ResponseEntity<Account> createAccount(@PathVariable(value = "customerId") Long customerId,
                                               @RequestBody Account accountRequest) {
    Account account = customerRepository.findById(customerId).map(customer -> {
      accountRequest.setCustomer(customer);
      return accountRepository.save(accountRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found Customer with id = " + customerId));
    return new ResponseEntity<>(account, HttpStatus.CREATED);
  }

    @PutMapping("/customers/{customerId}/accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") long id, @RequestBody Account accountRequest) {
      Account account = accountRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("AccountId " + id + "not found"));
      account.setAmount(accountRequest.getAmount());
      account.setAccountname(accountRequest.getAccountname());
      return new ResponseEntity<>(accountRepository.save(account), HttpStatus.OK);
    }

  @DeleteMapping("/customers/{customerId}/accounts/{id}")
  public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") long id) {
    accountRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/customers/{customerId}/accounts")
  public ResponseEntity<List<Account>> deleteAllAccountOfCustomerId(@PathVariable(value = "customerId") Long customerId) {
    if (!accountRepository.existsById(customerId)) {
      throw new ResourceNotFoundException("Not found Customer with id = " + customerId);
    }
    accountRepository.deleteByCustomerId(customerId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
