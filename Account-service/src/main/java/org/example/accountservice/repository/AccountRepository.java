package org.example.accountservice.repository;

import java.util.List;
import java.util.Optional;

import org.example.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("SELECT a FROM Account a LEFT JOIN FETCH a.address LEFT JOIN FETCH a.contacts")
	List<Account> findAll();
	
	@Query("SELECT a FROM Account a LEFT JOIN FETCH a.address LEFT JOIN FETCH a.contacts WHERE a.id = :id")
	Optional<Account> findById(Long id);

}
