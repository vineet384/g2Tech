package com.daimler.travelbook.roadtrip.rest;

import com.daimler.travelbook.roadtrip.domain.*;
import com.daimler.travelbook.roadtrip.model.Customer;
import com.daimler.travelbook.roadtrip.model.Supplier;
import com.daimler.travelbook.roadtrip.service.IDetailService;
import com.daimler.travelbook.roadtrip.util.ALog;
import com.daimler.travelbook.roadtrip.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class DetailController implements IDetailController {

    @Autowired
    IDetailService detailService;

    /**
     * Add customer into database
     * profile is either person or company
     * @param customerData
     * @return
     */
    @Override
    @ALog(startLog = true)
    public ResponseEntity<BaseResponse> addCustomer(@RequestBody Profile customerData) {
        BaseResponse response = new BaseResponse();
        String validateData = validateRequest(customerData); // Important:  A customer or supplier can be a person or a company, but not both
        if(validateData.equals(Constants.BADREQUEST)){
            response.setMessage("/Customer cant be person and company");
            response.setStatus(400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Customer cust = detailService.addCustomer(customerData,validateData);
            if(cust!= null){
                response.setMessage("Customer Added successfully - Customer type: "+validateData);
            }else{
                response.setMessage("Duplicate entry for - Customer type: "+validateData);
            }
            response.setStatus(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error while adding customer",e);
            response.setMessage("Error while adding customer");
            response.setStatus(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    /**
     * Add Supplier into database
     * profile is either person or company
     * @param supplierData
     * @return
     */
    @Override
    @ALog(startLog = true)
    public ResponseEntity<BaseResponse> addSupplier(Profile supplierData) {
        BaseResponse response = new BaseResponse();
        String validateData = validateRequest(supplierData); // Important:  A customer or supplier can be a person or a company, but not both
        if(validateData.equals(Constants.BADREQUEST)){
            response.setMessage("Bad request or Supplier cant be person and company");
            response.setStatus(400);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Supplier supplier = detailService.addSupplier(supplierData,validateData);
            if(supplier!= null){
                response.setMessage("Supplier Added successfully - Supplier type "+validateData);
            }else{
                response.setMessage("duplicate entry for - Supplier type "+validateData);
            }
            response.setStatus(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error while adding Supplier",e);
            response.setMessage("Error while adding Supplier");
            response.setStatus(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @return ProfileData list of Person
     */
    @Override
    @ALog(startLog = true)
    public ResponseEntity<ProfileData> getperson() {
        ProfileData response = new ProfileData();
        try{

            response.setStatus(200);
            response.setMessage("Person list");
            response.setPersonDataList(detailService.personList());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.setMessage("Error while fetching person");
            response.setStatus(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     *
     * @return ProfileData list of company
     */
    @Override
    @ALog(startLog = true)
    public ResponseEntity<ProfileData> getcompany() {
        ProfileData response = new ProfileData();
        try{

            response.setStatus(200);
            response.setMessage("Company list");
            response.setCompanyDataList(detailService.companyList());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.setMessage("Error while fetching Company");
            response.setStatus(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *  Important:  A customer or supplier can be a person or a company, but not both
     *
     * @param profile
     * @return
     */
    private String validateRequest(Profile profile) {

        if ((profile.getFirst_name() != null && profile.getLast_name() != null)
                && (profile.getRegistration_number() == null && profile.getName() == null)) {
            return Constants.PERSON;
        } else if ((profile.getFirst_name() == null && profile.getLast_name() == null)
                && (profile.getRegistration_number() != null && profile.getName() != null)) {

                return Constants.COMPANY;
        }else{
            return Constants.BADREQUEST;
        }
    }
}
