package com.javaAPI.spring.hibernate.repository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.javaAPI.spring.hibernate.model.Account;
import java.util.*;
public interface AccountRepository extends JpaRepository<Account, Long> {
  @Transactional
  void deleteByCustomerId(Long customerid);
    List<Account> findByCustomerId(Long customerid);
}
