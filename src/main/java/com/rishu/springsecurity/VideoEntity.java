package com.rishu.springsecurity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity // JPA annotation to signal this is a JPA-managed entity
public class VideoEntity {

    // @Id is JPA annotation to flag this field as the primary key
    // @GeneratedValue is JPA annotation to signal that the ID should be generated automatically
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;

    // JPA expects a no-arg constructor, that is either public or protected
    protected VideoEntity() {
        this(null, null);
    }

    // constructor designed for creating new entries in the database.
    // When the id field is null, it tells JPA we want to create a new row in the table
    VideoEntity(String name, String description) {
        this.id = null;
        this.name = name;
        this.description = description;
    }

    // getters and setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
