package com.patatus.crmparte2.repository;

import com.patatus.crmparte2.model.classes.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {
    // 1. A count of Leads by SalesRep can be displayed by typing "Report Lead by SalesRep"
        @Query("SELECT s.name,COUNT(l) FROM SalesRep s JOIN s.repLead l GROUP BY s")
        public List<Object[]> findLeadCountBySalesRep();

    // 2. A count of all Opportunities by SalesRep can be displayed by typing "Report Opportunity by SalesRep"
        @Query("SELECT s.name,COUNT(o) FROM SalesRep s LEFT JOIN s.repOpportunity o GROUP BY s")
        public List<Object[]> findOpportunityCountBySalesRep();

    // 3. A count of all CLOSED_WON Opportunities by SalesRep can be displayed by typing "Report CLOSED-WON by SalesRep"
    // 4. A count of all CLOSED_LOST Opportunities by SalesRep can be displayed by typing "Report CLOSED-LOST by SalesRep"
    // 5. A count of all OPEN Opportunities by SalesRep can be displayed by typing "Report OPEN by SalesRep"
    // The last three lines are checked with only one query, using status as a parameter.
        @Query("SELECT s.name,COUNT(cwo) FROM SalesRep s LEFT JOIN s.repOpportunity cwo WHERE cwo.status = :status GROUP BY s")
        public List<Object[]> findOpportunityByStatusCountBySalesRep(@Param("status")Enum status);
}
