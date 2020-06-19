package com.g2techsoft.assessment.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Profile {

    private String first_name;
    private String last_name;
    private String name;
    private String registration_number;
    private int areaCode;
    private int number;
    private Date last_order;
    private String taxNumber;
    private int Order_lead_time_in_days;

}
