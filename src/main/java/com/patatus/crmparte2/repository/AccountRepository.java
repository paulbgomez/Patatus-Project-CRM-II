package com.patatus.crmparte2.repository;

import com.patatus.crmparte2.model.classes.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    // Account count
    @Query("SELECT COUNT(id) FROM Account")
    Integer countAccount();

    // The mean employeeCount
    @Query("SELECT AVG(employeeCount) FROM Account")
    Double findMeanEmployeeCount();

    // The maximum employeeCount
    @Query(value="SELECT a.id, a.employee_count FROM account a ORDER BY a.employee_count DESC LIMIT 1", nativeQuery = true)
    List<Object[]> findMaxEmployeeCount();

    // The minimum employeeCount
    @Query(value="SELECT a.id, a.employee_count FROM account a ORDER BY a.employee_count ASC LIMIT 1", nativeQuery = true)
    List<Object[]> findMinEmployeeCount();

    // Ordered opportunities by account for the median
    @Query(value="SELECT oo.count FROM (SELECT COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id) AS oo ORDER BY count", nativeQuery = true)
    List<Object[]> findOpportunitiesByAccountOrdered();

    // Max opportunities by account
    @Query(value="SELECT a.id,COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id ORDER BY count DESC LIMIT 1", nativeQuery = true)
    List<Object[]> findMaxOpportunitiesByAccount();

    // Min opportunities by account
    @Query(value="SELECT a.id,COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id ORDER BY count ASC LIMIT 1", nativeQuery = true)
    List<Object[]> findMinOpportunitiesByAccount();

    // Average opportunities by account
    @Query(value="SELECT AVG(oo.count) FROM (SELECT COUNT(o.id) AS count FROM account a LEFT JOIN opportunity o ON a.id = o.account_id GROUP BY a.id) AS oo", nativeQuery = true)
    double findAvgOpportunitiesByAccount();



}
