package com.rishu.springsecurity;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


// Spring Data JPA provides a JpaRepository class that has CRUD methods
// such as save(), findAll(), findById(), deleteById(), etc.
// We can extend this interface to create our own repository interface
// VideoEntity is the entity class ( domain type ) and Long is the type of the primary key
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {

    // Custom finder
    // Spring Data JPA will automatically generate a query from this method name
    // Spring Data JPA will literally translate this method signature into a SQL query
    // SELECT * FROM video_entity WHERE name = ?1
//    List<VideoEntity> findByName(String name);
//
//    public List<VideoEntity> search(VideoSearch videoSearch);

    List<VideoEntity> findByNameContainsIgnoreCase(String partialName);

    List<VideoEntity> findByDescriptionContainsIgnoreCase(String partialDescription);

    // Basically, we were looking for a partial match on both fields, and the casing shouldn't matter.
    // ( ContainsAllIgnoreCase )
    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(String partialName, String partialDescription);

    // custom JPA ( JPQL ) query
    // @Query("select v from VideoEntity v where v.name == ?1")
    //  List<VideoEntity> findCustomerReport(String value);
    
}
