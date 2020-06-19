package com.g2techsoft.assessment.repo;

import com.g2techsoft.assessment.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person,Long> {

    @Query("SELECT p FROM Person p where p.first_name =:FIRST_NAME and p.last_name=:LAST_NAME")
    public Optional<Person> findPersonByCustomer(@Param("FIRST_NAME") String firstName, @Param("LAST_NAME")String LastName);

    @Query("SELECT p FROM Person p where p.first_name =:FIRST_NAME and p.last_name=:LAST_NAME ")
    public Optional<Person> findPersonBysupplier(@Param("FIRST_NAME") String firstName, @Param("LAST_NAME")String LastName);



}
