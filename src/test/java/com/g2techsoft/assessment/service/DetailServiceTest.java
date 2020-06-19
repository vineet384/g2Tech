package com.g2techsoft.assessment.service;

import com.g2techsoft.assessment.domain.Profile;
import com.g2techsoft.assessment.model.Company;
import com.g2techsoft.assessment.model.Customer;
import com.g2techsoft.assessment.model.Person;
import com.g2techsoft.assessment.model.PhoneNumber;
import com.g2techsoft.assessment.util.Constants;
import com.g2techsoft.assessment.repo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class DetailServiceTest {

    @Mock
    PersonRepo personRepo;
    @Mock
    CompanyRepo companyRepo;
    @Mock
    CustomerRepo customerRepo;
    @Mock
    SupplierRepo supplierRepo;
    @Mock
    PhoneNumberRepo phoneNumberRepo;

    @InjectMocks
    DetailService detailService;

    private MockHttpServletRequest request;
    private Profile customerData;
    private Person person;
    private PhoneNumber phoneNumber;
    private Company company;

    @BeforeEach
    void setUp() {

        request = new MockHttpServletRequest();
        customerData = new Profile();
        customerData.setFirst_name("test");
        customerData.setLast_name("test2");
        customerData.setAreaCode(91);
        customerData.setNumber(12378965);
        customerData.setName("CompanyName");
        customerData.setRegistration_number("787878VVFG");
        customerData.setTaxNumber("78545-2020");
        customerData.setOrder_lead_time_in_days(1254);

        person = new Person();
        person.setLast_name("test");
        person.setLast_name("test2");
        phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(456);
        phoneNumber.setAreaCode(91);
        List<PhoneNumber> phList = new ArrayList<>();
        phList.add(phoneNumber);
        person.setNumber(phList);
        company = new Company();
        company.setName("test");
        company.setPhoneNumber(phList);
        company.setRegistration_number("XXVFT");


    }

    @Test
    void addCustomerAsPersonForSuccess() throws Exception {
        Customer c = new Customer();
        c.setLast_order(new Date());
        c.setCustomer_number(phoneNumber);
        c.setId(1L);
        Mockito.when(phoneNumberRepo.save(Mockito.any(PhoneNumber.class))).thenReturn(phoneNumber);
        Mockito.when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(c);
        Mockito.when(personRepo.save(Mockito.any(Person.class))).thenReturn(person);
        final Customer customer = detailService.addCustomer(customerData, Constants.PERSON);
        assertEquals(customer, c);
    }

    @Test
    void addCustomerAsCompanyForSuccess() throws Exception {
        Customer c = new Customer();
        c.setLast_order(new Date());
        c.setCustomer_number(phoneNumber);
        c.setId(1L);
        Mockito.when(phoneNumberRepo.save(Mockito.any(PhoneNumber.class))).thenReturn(phoneNumber);
        Mockito.when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(c);
        Mockito.when(companyRepo.save(Mockito.any(Company.class))).thenReturn(company);
        final Customer customer = detailService.addCustomer(customerData, Constants.COMPANY);
        assertEquals(customer, c);
    }

    @Test
    void addCustomerAsDuplicateCompany() throws Exception {
        Customer c = new Customer();
        c.setLast_order(new Date());
        c.setCustomer_number(phoneNumber);
        c.setId(1L);
        company.setCustomerID(1);
        Optional<Company> OptCompany = Optional.of(company);
        Mockito.when(phoneNumberRepo.save(Mockito.any(PhoneNumber.class))).thenReturn(phoneNumber);
        Mockito.when(companyRepo.findCompanyByCustomer(Mockito.anyString(), Mockito.anyString())).thenReturn(OptCompany);
        final Customer customer = detailService.addCustomer(customerData, Constants.COMPANY);
        assertEquals(customer, null);
    }

    @Test
    void addCustomerAsPersonANDCompany() throws Exception {
        Customer c = new Customer();
        c.setLast_order(new Date());
        c.setCustomer_number(phoneNumber);
        c.setId(1L);
        company.setCustomerID(0);
        Optional<Company> OptCompany = Optional.of(company);
        Mockito.when(phoneNumberRepo.save(Mockito.any(PhoneNumber.class))).thenReturn(phoneNumber);
        Mockito.when(companyRepo.findCompanyByCustomer(Mockito.anyString(), Mockito.anyString())).thenReturn(OptCompany);
        Mockito.when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(c);
        Mockito.when(companyRepo.save(Mockito.any(Company.class))).thenReturn(company);
        final Customer customer = detailService.addCustomer(customerData, Constants.COMPANY);
        assertEquals(customer, c);
    }
    @Test
    void addSupplier() {
    }

    @Test
    void personList() {
    }

    @Test
    void companyList() {
    }
}