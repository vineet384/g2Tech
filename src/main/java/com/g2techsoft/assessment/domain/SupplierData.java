package com.g2techsoft.assessment.domain;

import lombok.Data;

@Data
public class SupplierData {

    private String first_name;
    private String last_name;
    private String name;
    private String registration_number;
    private String taxNumber;
    private int Order_lead_time_in_days;
    private int areaCode;
    private int number;
}
