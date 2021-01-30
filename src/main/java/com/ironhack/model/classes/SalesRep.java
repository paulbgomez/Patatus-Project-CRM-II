package com.ironhack.model.classes;

import javax.persistence.*;
import java.util.List;

@Entity
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "repLead")
    private List<Lead> repLead;
    @OneToMany(mappedBy = "repOpportunity")
    private List<Opportunity> repOpportunity;

    // To generate autoincrementID:
    private static int idGenerator = 0;

    //Constructors
    public SalesRep() {
    }

    public SalesRep(String name) {
        setId();
        setName(name);
    }

    //Getters & Setters
    public void setId() {
        this.id = generateId();
    }

    public static int generateId()  {
        return idGenerator++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
