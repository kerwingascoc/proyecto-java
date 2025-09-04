package com.nttdata.dockerized.postgresql.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection = "users")
public class User {

    @Id
    private String id; // MongoDB usa String para _id

    private String name;
    private String email;
    private Boolean active;
}
