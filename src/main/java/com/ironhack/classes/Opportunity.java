package com.ironhack.classes;

import com.ironhack.enums.*;

public class Opportunity {
    // Properties
    private int id;
    private int quantity;
    private Contact decisionMaker;
    private Status status;
    private Product product;

    // To generate autoincrementID:
    private static int idGenerator = 0;

    //Constructor
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
                " | product: " + product;
    }

    //Getters and setters
    public void setId() {
        this.id = generateId();
    }

    public static int generateId()  {
        return idGenerator++;
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
}
