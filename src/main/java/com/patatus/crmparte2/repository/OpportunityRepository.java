package com.patatus.crmparte2.repository;

import com.patatus.crmparte2.model.classes.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    //Order to make the median query
    @Query ("SELECT o.quantity FROM Opportunity o ORDER BY o.quantity")
    List<Object[]> orderOpportunities();

    //The max quantity queries
    @Query(value="SELECT s.name, o.quantity FROM opportunity o INNER JOIN sales_rep s ON o.rep_opportunity_id = s.id ORDER BY o.quantity DESC LIMIT 1", nativeQuery = true)
    List<Object[]> findMaxQuantityByRepContainingName();

    @Query("SELECT MAX(o.quantity) FROM Opportunity o ")
    Integer findMaxQuantityFromOpportunities();

    @Query("SELECT MAX(o.quantity) FROM Opportunity o WHERE o.status = :status")
    Integer findMaxQuantityFromOpportunitiesByStatus(@Param("status") Enum status);

    //The min quantity queries
    @Query("SELECT MIN(o.quantity) FROM Opportunity o ")
    Integer findMinQuantityFromOpportunities();

    @Query("SELECT MIN(o.quantity) FROM Opportunity o WHERE o.status = :status")
    Integer findMinQuantityFromOpportunitiesByStatus(@Param("status") Enum status);

    @Query(value="SELECT s.name, o.quantity FROM opportunity o INNER JOIN sales_rep s ON o.rep_opportunity_id = s.id ORDER BY o.quantity ASC LIMIT 1", nativeQuery = true)
    List<Object[]> findMinQuantityByRepContainingName();

    //The mean quantity queries
    @Query("SELECT AVG(o.quantity) FROM Opportunity o")
    double findAverageQuantityFromOpportunities();

    // A count of all Opportunities by product
    @Query("SELECT o.product, COUNT(o) FROM Opportunity o GROUP BY o.product")
    List<Object[]> findOpportunitiesByProduct();

    // A count of all Opportunities by product and Status
    @Query("SELECT o.product, COUNT(o.status) FROM Opportunity o WHERE o.status=:status GROUP BY o.product")
    List<Object[]> findByProductAndStatus(@Param("status") Enum status);

    // A count of all Opportunities by country
    @Query("SELECT a.country, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a GROUP BY a.country")
    List<Object[]> findOpportunitiesByCountry();

    // A count of all Opportunities by country and Status
    @Query("SELECT a.country, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a WHERE o.status=:status GROUP BY a.country")
    List<Object[]> findByCountryAndStatus(@Param("status") Enum status);

    // A count of all Opportunities by city
    @Query("SELECT a.city, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a GROUP BY a.city")
    List<Object[]> findOpportunitiesByCity();

    // A count of all Opportunities by city and Status
    @Query("SELECT a.city, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a WHERE o.status=:status GROUP BY a.city")
    List<Object[]> findByCityAndStatus(@Param("status") Enum status);

    // A count of all Opportunities by industry
    @Query("SELECT a.industry, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a GROUP BY a.industry")
    List<Object[]> findOpportunitiesByIndustry();

    // A count of all Opportunities by industry and Status
    @Query("SELECT a.industry, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a WHERE o.status=:status GROUP BY a.industry")
    List<Object[]> findByIndustryAndStatus(@Param("status") Enum status);

}
