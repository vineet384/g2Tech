package com.daimler.travelbook.roadtrip.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(mappedBy = "customerNumber",cascade=CascadeType.ALL)
    private PhoneNumber customer_number;
    private Date last_order;

}
