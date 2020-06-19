package com.g2techsoft.assessment.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String first_name;
    private String last_name;
    private long customerID;
    private long supplierID;
    @OneToMany(mappedBy="person",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneNumber> number;
    private int phoneId;



}
