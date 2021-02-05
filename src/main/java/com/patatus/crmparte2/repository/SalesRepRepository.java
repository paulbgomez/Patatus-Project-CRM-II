package com.patatus.crmparte2.repository;

import com.patatus.crmparte2.model.classes.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {
    //A count of Leads by SalesRep
    @Query("SELECT s.name,COUNT(l) FROM Lead l INNER JOIN l.repLead s GROUP BY s")
    List<Object[]> findLeadCountBySalesRep();

    //@Query(value="SELECT s.name,COUNT(*) FROM leads l INNER JOIN sales_rep s ON l.rep_lead_id=s.id  GROUP BY s.name",nativeQuery = true)
    //public List<Object[]> findLeadCountBySalesRep();

    //A count of all Opportunities by SalesRep
    @Query("SELECT s.name,COUNT(o) FROM SalesRep s LEFT JOIN s.repOpportunity o GROUP BY s")
    List<Object[]> findOpportunityCountBySalesRep();


    //A count of Opportunities by Status and Sales Rep
    @Query("SELECT s.name,COUNT(o) FROM SalesRep s LEFT JOIN s.repOpportunity o WHERE o.status = :status GROUP BY s")
    List<Object[]> findOpportunityByStatusCountBySalesRep(@Param("status") Enum status);

    @Query("SELECT COUNT(id) FROM Account")
    Integer countSalesRep();

}
