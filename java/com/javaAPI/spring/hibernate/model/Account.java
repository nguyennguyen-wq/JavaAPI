package com.javaAPI.spring.hibernate.model;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "accounts")
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
  private Long id;
  private String accountname;
  private long amount;
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "customer_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Customer customer;
  public Long getId() {
    return id;
  }
  public String getAccountname() {
    return accountname;
  }
  public void setAccountname(String accountname) {
    this.accountname = accountname;
  }
  public long getAmount() {
    return amount;
  }
  public void setAmount(long amount) {
    this.amount = amount;
  }
  public Customer getCustomer() {
    return customer;
  }
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

}
