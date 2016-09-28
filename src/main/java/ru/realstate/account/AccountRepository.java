package ru.realstate.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>  {
	Account findOneByEmail(String email);

	@Query("select a.id, a.email, a.role  FROM Account a")
	Page<Account> getAllAccounts(Pageable pageable);

}