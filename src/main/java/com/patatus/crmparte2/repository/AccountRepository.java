package com.patatus.crmparte2.repository;

import com.patatus.crmparte2.model.classes.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    // The mean employeeCount
    @Query("SELECT AVG(employeeCount) FROM Account")
    public Double findMeanEmployeeCount();

    // 2. The median employeeCount can be displayed by typing "Median EmployeeCount"

    // The maximum employeeCount
    @Query(value="SELECT a.id, a.employee_count FROM account a ORDER BY a.employee_count DESC LIMIT 1", nativeQuery = true)
    public List<Object[]> findMaxEmployeeCount();

    // The minimum employeeCount
    @Query(value="SELECT a.id, a.employee_count FROM account a ORDER BY a.employee_count ASC LIMIT 1", nativeQuery = true)
    public List<Object[]> findMinEmployeeCount();

    // Ordered opportunities by account for the median
    @Query(value="SELECT oo.count FROM (SELECT COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id) AS oo ORDER BY count", nativeQuery = true)
    public List<Object[]> findOpportunitiesByAccountOrdered();

    // Max opportunities by account
    @Query(value="SELECT a.id,COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id ORDER BY count DESC LIMIT 1", nativeQuery = true)
    public List<Object[]> findMaxOpportunitiesByAccount();

    // Min opportunities by account
    @Query(value="SELECT a.id,COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id ORDER BY count ASC LIMIT 1", nativeQuery = true)
    public List<Object[]> findMinOpportunitiesByAccount();

    // Average opportunities by account
    @Query(value="SELECT AVG(oo.count) FROM (SELECT COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id) AS oo", nativeQuery = true)
    public double findAvgOpportunitiesByAccount();



}
