package com.daimler.travelbook.roadtrip.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int areaCode;
    private int number;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="phoneId", nullable=true)
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="phoneId", nullable = true, insertable = false, updatable = false)
    private Company company;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = true,referencedColumnName = "id")
    private Customer customerNumber;
}
