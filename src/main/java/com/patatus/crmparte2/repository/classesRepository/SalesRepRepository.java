package com.patatus.crmparte2.repository.classesRepository;

import com.patatus.crmparte2.model.classes.SalesRep;
import com.patatus.crmparte2.model.enums.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {


    // He hecho estas pequeñas pruebas para comprobar que funcionaba la DB:

    @Query("SELECT s FROM SalesRep s JOIN FETCH s.repLead WHERE s.name = :name")
    public Optional<SalesRep> findSalesRepWithLeadsByName(@Param("name") String name);

    @Query("SELECT s FROM SalesRep s JOIN FETCH s.repLead l WHERE l.name = :leadName")
    public Optional<SalesRep> findSalesRepByLeadName(@Param("leadName") String leadName);

    @Query("SELECT s FROM SalesRep s JOIN FETCH s.repOpportunity o WHERE o.product = :product")
    public Optional<SalesRep> findSalesRepByProduct(@Param("product") Product product);
}
