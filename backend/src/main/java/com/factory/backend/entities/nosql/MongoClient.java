package com.factory.backend.entities.nosql;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("clients")
public class MongoClient {
    @Id
    private ObjectId id;

    @Indexed(unique=true)
    @Field(name = "phone_number")
    private String phoneNumber;

    @Field(name = "organization_name")
    private String organizationName;

    @Field(name = "email")
    private String email;

    @Field(name = "legal_address")
    private String legalAddress;
}
