package com.xandxr.mongodemo.controllers;

import com.xandxr.mongodemo.services.LeadsService;
import com.xandxr.mongodemo.domain.Lead;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class LeadsController {
    private final LeadsService leadsService;

    @Autowired
    public LeadsController(@Qualifier("leadsService") LeadsService leadsService) {
        this.leadsService = leadsService;
    }

    @PostMapping("/create-lead")
    public ResponseEntity<String> createLead(@RequestBody Lead lead) {

        System.out.println("Incoming lead: " + System.lineSeparator() + lead);
        leadsService.createLead(lead);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Lead creation successful" + System.lineSeparator() + lead);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Lead> getLeadWithEmail(@PathVariable("email") String email) {
        Lead lead = leadsService.queryLeadWithEmail(email);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Controller", "LeadsController");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .body(lead);
    }

    @GetMapping("/{firstName}/{lastName}")
    public ResponseEntity<Lead> getLeadWithFirstAndLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        // query database to obtain lead
        Lead foundLead = leadsService.queryLeadWithFirstNameAndLastName(firstName, lastName);

        // create and set headers for response
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "LeadsController");
        headers.add("secret", "the_secret");

        // return the response with headers injected and passing the retrieved entity(in this case it's a Lead obj) in the body of the response
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .body(foundLead);
    }

    // TODO
    // work on logic to update a lead
    @PutMapping("/{id}")
    public ResponseEntity<Lead> updateById(@PathVariable("id") String id) {
        Lead lead = leadsService.updateLeadById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "LeadsController");

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .body(lead);
    }
}
