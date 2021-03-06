package com.patatus.crmparte2.model.classes;

import com.patatus.crmparte2.menu.MenuColors;
import com.patatus.crmparte2.model.enums.Product;
import com.patatus.crmparte2.model.enums.Status;

import javax.persistence.*;

@Entity
public class Opportunity {
    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    @OneToOne
    private Contact decisionMaker;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Product product;

    @ManyToOne
    private SalesRep repOpportunity;
    @ManyToOne
    private Account account;

    // Constructors:
    public Opportunity() {
    }

    public Opportunity(Contact decisionMaker, Product product, int quantity, SalesRep repOpportunity, Account account) {
        setId();
        setDecisionMaker(decisionMaker);
        status = Status.OPEN;
        setProduct(product);
        setQuantity(quantity);
        setRepOpportunity(repOpportunity);
        setAccount(account);
    }

    @Deprecated
    public Opportunity(Contact decisionMaker, Product product, int quantity, SalesRep repOpportunity) {
        setId();
        setDecisionMaker(decisionMaker);
        status = Status.OPEN;
        setProduct(product);
        setQuantity(quantity);
        setRepOpportunity(repOpportunity);
    }

    @Deprecated
    public Opportunity(Contact decisionMaker, Product product, int quantity) {
        setId();
        setDecisionMaker(decisionMaker);
        status = Status.OPEN;
        setProduct(product);
        setQuantity(quantity);
    }

    // -----------------Methods------------------

    // Method to call from menu --> to close an opportunity as successful.
    public void closeWon(){
        this.setStatus(Status.CLOSED_WON);
        System.out.println("The opportunity " + this.getId() + " is won. Closing it.");
    }

    // Method to call from menu --> to close an opportunity as lost
    public void closeLost(){
        this.setStatus(Status.CLOSED_LOST);
        System.out.println("The opportunity " + this.getId() + " is lost. Closing it.");
    }

    // Override of the toString() method to display the Opportunities in a more friendly way.
    @Override
    public String toString() {
        return "ID-" + id +
                " | quantity: " + quantity +
                " | decisionMaker: ID-" + decisionMaker.getId() + " " + decisionMaker.getName() +
                " | status: " + status +
                " | product: " + product +
                MenuColors.setColorRed(" <-> ") + "SalesRep ID-" + getRepOpportunity().getId() +
                " | name: " + getRepOpportunity().getName();
    }

    //Getters and setters
    public void setId() {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Contact getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contact decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SalesRep getRepOpportunity() {
        return repOpportunity;
    }

    public void setRepOpportunity(SalesRep repOpportunity) {
        this.repOpportunity = repOpportunity;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
