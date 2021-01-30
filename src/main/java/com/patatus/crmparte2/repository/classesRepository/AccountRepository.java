package com.patatus.crmparte2.repository.classesRepository;

import com.patatus.crmparte2.model.classes.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
