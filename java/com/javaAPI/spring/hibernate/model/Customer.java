package com.javaAPI.spring.hibernate.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
@Entity
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
  @JsonIgnore 
  private Long id;
  @Column(name = "firstname")
  private String firstname;
  @Column(name = "lastname")
  private String lastname;
  @Column(name = "socialsecuritynumber")
  private long socialsecuritynumber;
  public Customer() {
  }

  public Customer(String firstname, String lastname, long socialsecuritynumber) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.socialsecuritynumber = socialsecuritynumber;
  }

    public Customer(Long id, String firstname, String lastname,  long socialsecuritynumber) {
      this.id = id;
      this.firstname = firstname;
      this.lastname = lastname;
      this.socialsecuritynumber = socialsecuritynumber;
    }

    public Long getId() {
    return id;
  }
    public void setId(Long id) {
    this.id = id;
  }
    public String getFirstname() {
    return firstname;
  }
    public void setFirstname(String firstname) {
    this.firstname = firstname;
  }
    public String getLastname() {
    return lastname;
  }
    public void setLastname(String lastname) {
    this.lastname = lastname;
  }
    public Long getSocialsecuritynumber() {
    return socialsecuritynumber;
  }
    public void setSocialsecuritynumber(long socialsecuritynumber) {
    this.socialsecuritynumber = socialsecuritynumber;
  }

  @Override
  public String toString() {
    return "Customer [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname  + "]";
  }
}
