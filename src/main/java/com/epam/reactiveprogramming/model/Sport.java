package com.epam.reactiveprogramming.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "sports")
public class Sport {

    @Id
    public String id;
    public String name;

    public Sport(String name) {
        this.name = name;
    }
}
