package com.patatus.crmparte2.repository.classesRepository;

import com.patatus.crmparte2.model.classes.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
