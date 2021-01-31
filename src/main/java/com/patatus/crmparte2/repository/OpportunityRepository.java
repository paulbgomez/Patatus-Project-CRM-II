package com.patatus.crmparte2.repository;

import com.patatus.crmparte2.model.classes.Opportunity;
import com.patatus.crmparte2.model.classes.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {
    @Query("SELECT s.name, MAX(o.quantity) FROM Opportunity o INNER JOIN o.repOpportunity s GROUP BY s.name")
    public List<Object[]> findMaxQuantityByRepContainingName ();

    @Query("SELECT MAX(o.quantity) FROM Opportunity o ")
    public Integer findMaxQuantityFromOpportunities ();

    @Query("SELECT MAX(o.quantity) FROM Opportunity o WHERE o.status = :status")
    public Integer findMaxQuantityFromWonOpportunities (@Param("status") Enum status);

    @Query("SELECT MIN(o.quantity) FROM Opportunity o ")
    public Integer findMinQuantityFromOpportunities ();

    @Query("SELECT MIN(o.quantity) FROM Opportunity o WHERE o.status = :status")
    public Integer findMinQuantityFromWonOpportunities (@Param("status") Enum status);
}
