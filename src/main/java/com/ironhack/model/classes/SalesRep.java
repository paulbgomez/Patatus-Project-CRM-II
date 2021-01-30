package com.ironhack.model.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SalesRep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

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
