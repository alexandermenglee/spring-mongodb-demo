package com.xandxr.mongodemo.services;

import com.xandxr.mongodemo.domain.Lead;
import com.xandxr.mongodemo.repositories.LeadsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("leadsService")
public class LeadsServiceImpl implements LeadsService {
    final LeadsRepository repository;

    @Autowired
    public LeadsServiceImpl(LeadsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Lead createLead(Lead lead) {
        repository.insert(lead);
        return lead;
    }

    @Override
    public Lead queryLeadWithEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Lead queryLeadWithFirstNameAndLastName(String firstName, String lastName) {
        Lead[] leadStorage = new Lead[1];

        repository.findByFirstNameAndLastName(firstName, lastName).
                ifPresentOrElse(l -> {
                    leadStorage[0] = l;
                    System.out.println("FOUND LEAD WITH FIRST & LAST NAME" + System.lineSeparator() + l);
                }, () -> {
                    System.out.println("No lead found with the values: " + firstName + " " + lastName);
                });

        return leadStorage[0];
    }

    @Override
    public Lead updateLeadById(String id) {
        return new Lead();
    }
}
