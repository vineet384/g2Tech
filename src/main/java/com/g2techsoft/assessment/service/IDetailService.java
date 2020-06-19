package com.g2techsoft.assessment.service;

import com.g2techsoft.assessment.domain.CompanyData;
import com.g2techsoft.assessment.domain.PersonData;
import com.g2techsoft.assessment.domain.Profile;
import com.g2techsoft.assessment.model.Customer;
import com.g2techsoft.assessment.model.Supplier;

import java.util.List;

public interface IDetailService {

    public Customer addCustomer(Profile customerData, String customerType) throws Exception;

    public Supplier addSupplier(Profile supplierData, String customerType) throws Exception;

    public List<PersonData> personList() throws Exception;

    public List<CompanyData> companyList() throws Exception;
}
