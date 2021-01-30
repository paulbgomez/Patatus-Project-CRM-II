package com.patatus.crmparte2.repository.classesRepository;

import com.patatus.crmparte2.model.classes.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
}
