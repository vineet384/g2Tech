package com.daimler.travelbook.roadtrip.service;

import com.daimler.travelbook.roadtrip.domain.*;
import com.daimler.travelbook.roadtrip.model.Customer;
import com.daimler.travelbook.roadtrip.model.Supplier;

import java.util.List;

public interface IDetailService {

    public Customer addCustomer(Profile customerData, String customerType) throws Exception;

    public Supplier addSupplier(Profile supplierData, String customerType) throws Exception;

    public List<PersonData> personList() throws Exception;

    public List<CompanyData> companyList() throws Exception;
}
