package com.patatus.crmparte2.repository;

import com.patatus.crmparte2.model.classes.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    /*
    The max quanitity of products order can be displayed by typing "Max Quantity"
     */
    @Query(value="SELECT s.name, o.quantity FROM opportunity o INNER JOIN sales_rep s ON o.rep_opportunity_id = s.id ORDER BY o.quantity DESC LIMIT 1", nativeQuery = true)
    List<Object[]> findMaxQuantityByRepContainingName();

    @Query("SELECT MAX(o.quantity) FROM Opportunity o ")
    Integer findMaxQuantityFromOpportunities();

    @Query("SELECT MAX(o.quantity) FROM Opportunity o WHERE o.status = :status")
    Integer findMaxQuantityFromWonOpportunities(@Param("status") Enum status);

    /*
    The min quanitity of products order can be displayed by typing "Min Quantity"
     */
    @Query("SELECT MIN(o.quantity) FROM Opportunity o ")
    Integer findMinQuantityFromOpportunities();

    @Query("SELECT MIN(o.quantity) FROM Opportunity o WHERE o.status = :status")
    Integer findMinQuantityFromWonOpportunities(@Param("status") Enum status);

    @Query(value="SELECT s.name, o.quantity FROM opportunity o INNER JOIN sales_rep s ON o.rep_opportunity_id = s.id ORDER BY o.quantity ASC LIMIT 1", nativeQuery = true)
    List<Object[]> findMinQuantityByRepContainingName();

    /*
    The mean quanitity of products order can be displayed by typing "Mean Quantity"
     */
    @Query("SELECT AVG(o.quantity) FROM Opportunity o")
    double findAverageQuantityFromOpportunities();

    /*
   The median quanitity of products order can be displayed by typing "Mean Quantity"
    */
    @Query(value ="SELECT o.quantity Median FROM (SELECT o1.quantity, COUNT(o1.quantity) Rank FROM opportunity o1, opportunity o2 WHERE o1.quantity < o2.quantity OR (o1.quantity=o2.quantity) GROUP o1.quantity ORDER BY o1.quantity DESC) o3 WHERE Rank = (SELECT (COUNT(*)+1) DIV 2 FROM Opportunity)", nativeQuery = true)
    Integer findMedianFromOpportunities();

    // A count of all Opportunities by product can be displayed by typing "Report Opportunity by product"
    @Query("SELECT o.product, COUNT(o) FROM Opportunity o GROUP BY o.product")
    List<Object[]> findOpportunitiesByProduct();

    // A count of all CLOSED_WON Opportunities by product can be displayed by typing "Report CLOSED-WON by product"
    // A count of all CLOSED_LOST Opportunities by product can be displayed by typing "Report CLOSED-LOST by product"
    // A count of all OPEN Opportunities by product can be displayed by typing "Report OPEN by product"
    // Instead of doing 3 separate methods, we have created one by which, passing it the required status,
    // gives you a report of all the opportunities that are with this status
    // TODO HACER TEST
    @Query("SELECT o.product, COUNT(o.status) FROM Opportunity o WHERE o.status=:status GROUP BY o.product")
    List<Object[]> findByProductAndStatus(@Param("status") Enum status);

    // A count of all Opportunities by country can be displayed by typing "Report Opportunity by Country"
    // TODO HACER TEST
    @Query("SELECT a.country, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a GROUP BY a.country")
    List<Object[]> findOpportunitiesByCountry();

    // A count of all CLOSED_WON Opportunities by country can be displayed by typing "Report CLOSED-WON by Country"
    // A count of all CLOSED_LOST Opportunities by country can be displayed by typing "Report CLOSED-LOST by Country"
    // A count of all OPEN Opportunities by country can be displayed by typing "Report OPEN by Country"
    // TODO HACER TEST
    @Query("SELECT a.country, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a WHERE o.status=:status GROUP BY a.country")
    List<Object[]> findByCountryAndStatus(@Param("status") Enum status);

    // A count of all Opportunities by city can be displayed by typing "Report Opportunity by City"
    // TODO HACER TEST
    @Query("SELECT a.city, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a GROUP BY a.country")
    List<Object[]> findOpportunitiesByCity();

    // A count of all CLOSED_WON Opportunities by city can be displayed by typing "Report CLOSED-WON by City"
    // A count of all CLOSED_LOST Opportunities by city can be displayed by typing "Report CLOSED-LOST by City"
    // A count of all OPEN Opportunities by city can be displayed by typing "Report OPEN by City"
    // TODO HACER TEST
    @Query("SELECT a.city, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a WHERE o.status=:status GROUP BY a.city")
    List<Object[]> findByCityAndStatus(@Param("status") Enum status);

    // A count of all Opportunities by industry can be displayed by typing "Report Opportunity by Industry"
    // TODO HACER TEST
    @Query("SELECT a.industry, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a GROUP BY a.industry")
    List<Object[]> findOpportunitiesByIndustry();

    // A count of all CLOSED_WON Opportunities by industry can be displayed by typing "Report CLOSED-WON by Industry"
    // A count of all CLOSED_LOST Opportunities by industry can be displayed by typing "Report CLOSED-LOST by Industry"
    // A count of all OPEN Opportunities by industry can be displayed by typing "Report OPEN by Industry"
    // TODO HACER TEST
    @Query("SELECT a.industry, COUNT(o.id) FROM Opportunity o RIGHT JOIN o.account a WHERE o.status=:status GROUP BY a.industry")
    List<Object[]> findByIndustryAndStatus(@Param("status") Enum status);


}
