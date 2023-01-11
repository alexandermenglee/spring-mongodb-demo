package com.xandxr.mongodemo.services;

import com.xandxr.mongodemo.domain.Lead;

public interface LeadsService {
    Lead createLead(Lead lead);

    Lead queryLeadWithEmail(String email);

    Lead queryLeadWithFirstNameAndLastName(String firstName, String lastName);

    Lead updateLeadById(String id);
}
