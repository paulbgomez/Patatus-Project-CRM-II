package com.patatus.crmparte2.model.classes;

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

    public SalesRep() {
    }

    public SalesRep(String name) {
        setName(name);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lead> getRepLead() {
        return repLead;
    }

    public void setRepLead(List<Lead> repLead) {
        this.repLead = repLead;
    }

    public List<Opportunity> getRepOpportunity() {
        return repOpportunity;
    }

    public void setRepOpportunity(List<Opportunity> repOpportunity) {
        this.repOpportunity = repOpportunity;
    }

    @Override
    public String toString() {
        return "SalesRep{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
