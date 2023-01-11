package com.xandxr.mongodemo.domain;

import com.mongodb.lang.Nullable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
public class Lead {
    @Id
    private String id;
    @Nullable
    private UUID leadUniqueIdentifier;
    private String firstName;
    private String lastName;
    private String stateCode;
    private String phoneNumber;
    private String email;
    private String type;
}
