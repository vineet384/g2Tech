package com.daimler.travelbook.roadtrip.service;

import com.daimler.travelbook.roadtrip.domain.*;
import com.daimler.travelbook.roadtrip.model.*;
import com.daimler.travelbook.roadtrip.repo.*;
import com.daimler.travelbook.roadtrip.util.ALog;
import com.daimler.travelbook.roadtrip.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetailService implements IDetailService {

    @Autowired
    PersonRepo personRepo;
    @Autowired
    CompanyRepo companyRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    SupplierRepo supplierRepo;
    @Autowired
    PhoneNumberRepo phoneNumberRepo;

    /**
     *
     * @param customerData
     * @param customerType : person or company
     * @return
     * @throws Exception
     */
    @Override
    @ALog
    public Customer addCustomer(Profile customerData, String customerType) throws Exception {
        Customer customer = new Customer();
        PhoneNumber ph = new PhoneNumber();
        List<PhoneNumber> phLst = new ArrayList<>();
        ph.setAreaCode(customerData.getAreaCode());
        ph.setNumber(customerData.getNumber());
        customer.setLast_order(customerData.getLast_order());
            if(customerType.equals(Constants.PERSON)){
                /**
                 * Important : A company or person can be a supplier and a customer at the same time
                 *
                 * if person already available in DB as a supplier
                 * find person based on first name and last name where customerID is zero or null
                 */

                Optional<Person> p = personRepo.findPersonByCustomer(customerData.getFirst_name(),customerData.getLast_name());
                if(p.isPresent()){

                    Person personFromDB = p.get();
                    if(personFromDB.getCustomerID() == 0) {
                        ph = phoneNumberRepo.save(ph);
                        customer.setCustomer_number(ph);
                        customer = customerRepo.save(customer);
                        personFromDB.setCustomerID(customer.getId());
                        personRepo.save(personFromDB);
                    }else{
                        return null;
                    }
                }else {
                    Person person = new Person();
                    ph.setAreaCode(customerData.getAreaCode());
                    ph.setNumber(customerData.getNumber());
                    ph = phoneNumberRepo.save(ph);
                    customer.setCustomer_number(ph);
                    customer = customerRepo.save(customer);
                    person.setFirst_name(customerData.getFirst_name());
                    person.setLast_name(customerData.getLast_name());
                    person.setCustomerID(customer.getId());
                    phLst.add(ph);
                    person.setPhoneId((int) ph.getId());
                    person.setNumber(phLst);
                    personRepo.save(person);
                }
            }else{
                /**
                 * Important : A company or person can be a supplier and a customer at the same time
                 *
                 * if company  already available in DB as a supplier
                 * find person based on name and registration number where customerID is zero or null
                 */
                Optional<Company> c = companyRepo.findCompanyByCustomer(customerData.getName(),customerData.getRegistration_number());
                if(c.isPresent()) {
                    Company companyDB = c.get();
                   if(companyDB.getCustomerID() == 0){
                       customer.setCustomer_number(ph);
                       customer = customerRepo.save(customer);
                       companyDB.setCustomerID(customer.getId());
                       companyRepo.save(companyDB);
                   }else{
                       return null;
                   }

                }else{
                    ph = phoneNumberRepo.save(ph);
                    phLst.add(ph);
                    customer.setCustomer_number(ph);
                    customer = customerRepo.save(customer);
                    Company company = new Company();
                    company.setName(customerData.getName());
                    company.setRegistration_number(customerData.getRegistration_number());
                    company.setCustomerID(customer.getId());
                    company.setPhoneNumber(phLst);
                    company.setPhoneId((int) ph.getId());
                    companyRepo.save(company);
                }

            }
        return customer;
    }

    @Override
    @ALog
    public Supplier addSupplier(Profile supplierData, String customerType) throws Exception {
        Supplier supplier = new Supplier();
            PhoneNumber ph = new PhoneNumber();
            List<PhoneNumber> phLst = new ArrayList<>();
            supplier.setTax_number(supplierData.getTaxNumber());
            supplier.setOrder_lead_time_in_days(supplierData.getOrder_lead_time_in_days());
            supplier = supplierRepo.save(supplier);
            if(customerType.equals(Constants.PERSON)){
                /**
                 * Important : A company or person can be a supplier and a customer at the same time
                 *
                 * if person already available in DB as a customer
                 * find person based on first name and last name where customerID is zero or null
                 */

                Optional<Person> p = personRepo.findPersonBysupplier(supplierData.getFirst_name(),supplierData.getLast_name());
                if(p.isPresent()){
                    Person personFromDB = p.get();
                    if(personFromDB.getSupplierID() == 0) {
                        personFromDB.setSupplierID(supplier.getId());
                        personRepo.save(personFromDB).getId();
                    }else{
                        return null;
                    }
                }else {
                    Person person = new Person();
                    ph.setAreaCode(supplierData.getAreaCode());
                    ph.setNumber(supplierData.getNumber());
                    ph = phoneNumberRepo.save(ph);
                    person.setFirst_name(supplierData.getFirst_name());
                    person.setLast_name(supplierData.getLast_name());
                    person.setCustomerID(supplier.getId());
                    phLst.add(ph);
                    person.setPhoneId((int) ph.getId());
                    person.setNumber(phLst);
                    personRepo.save(person);
                }
            }else{
                /**
                 * Important : A company or person can be a supplier and a customer at the same time
                 *
                 * if company already available in DB as a customer
                 * find person based on first name and last name where customerID is zero or null
                 */
                Optional<Company> c = companyRepo.findCompanyBySupplierID(supplierData.getName(),supplierData.getRegistration_number());
                if(c.isPresent()){
                    Company companyDB = c.get();
                    if(companyDB.getSupplierID()==0) {
                        companyDB.setCustomerID(supplier.getId());
                        companyRepo.save(companyDB);
                    }
                }else{
                    ph = phoneNumberRepo.save(ph);
                    phLst.add(ph);
                    Company company = new Company();
                    company.setName(supplierData.getName());
                    company.setRegistration_number(supplierData.getRegistration_number());
                    company.setSupplierID(supplier.getId());
                    company.setPhoneNumber(phLst);
                    company.setPhoneId((int) ph.getId());
                    companyRepo.save(company);
                }
            }
            return supplier;
    }

    @Override
    @ALog
    public List<PersonData> personList() throws Exception {
         List<Person> personList =personRepo.findAll();
        List<PersonData> personData = new ArrayList<>();

        personList.stream().forEach(p->{
            PersonData personData1 = new PersonData();
            personData1.setFirst_name(p.getFirst_name());
            personData1.setLast_name(p.getLast_name());
            personData.add(personData1);
        });

         return personData;
    }

    @Override
    @ALog
    public List<CompanyData> companyList() throws Exception {
        List<CompanyData> companyDataList = new ArrayList<>();
        companyRepo.findAll().stream().forEach(c->{
            CompanyData companyData = new CompanyData();
            companyData.setName(c.getName());
            companyData.setRegistration_number(c.getRegistration_number());
            companyDataList.add(companyData);
        });
        return companyDataList;
    }


}
