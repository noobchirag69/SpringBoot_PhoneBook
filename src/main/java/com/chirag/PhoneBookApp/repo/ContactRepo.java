package com.chirag.PhoneBookApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chirag.PhoneBookApp.model.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {

}
