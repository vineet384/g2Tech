package com.g2techsoft.assessment.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Company")
public class Company {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private long id;
     private String name;
     private String registration_number;
     private long customerID;
     private long supplierID;
     @OneToMany(mappedBy="company",cascade = CascadeType.ALL, orphanRemoval = true)
     private List<PhoneNumber> phoneNumber;
     private int phoneId;
}
