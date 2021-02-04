package com.patatus.crmparte2.repository;

import com.patatus.crmparte2.model.classes.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    // 1. The mean employeeCount can be displayed by typing "Mean EmployeeCount"
    @Query("SELECT AVG(employeeCount) FROM Account")
    public Double findMeanEmployeeCount();

    // 2. The median employeeCount can be displayed by typing "Median EmployeeCount"
    // TODO ¿Quizá me traigo los valores a pelo y lo hago con java? :'D

    // 3. The maximum employeeCount can be displayed by typing "Max EmployeeCount"
    @Query(value="SELECT a.id, a.employee_count FROM account a ORDER BY a.employee_count DESC LIMIT 1", nativeQuery = true)
    public List<Object[]> findMaxEmployeeCount();

    // 4. The minimum employeeCount can be displayed by typing "Min EmployeeCount"
    @Query(value="SELECT a.id, a.employee_count FROM account a ORDER BY a.employee_count ASC LIMIT 1", nativeQuery = true)
    public List<Object[]> findMinEmployeeCount();
}
