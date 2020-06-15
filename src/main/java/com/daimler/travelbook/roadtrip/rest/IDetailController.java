package com.daimler.travelbook.roadtrip.rest;

import com.daimler.travelbook.roadtrip.domain.*;
import com.daimler.travelbook.roadtrip.util.Constants;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public interface IDetailController {
    /**
     *
     * @param customerData
     * @return BaseResponse
     * code = 200, message = Customer added successfully
     * code = 500, message = Internal Server Error
     * code = 400, message = Bad Request
     */

    @RequestMapping(value = Constants.REQUEST_MAPPING_PATH + "/addcustomer", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> addCustomer(@RequestBody Profile customerData);

    /**
     *
     * @param supplierData
     * @return BaseResponse
     *
     * code = 200, message = Customer added successfully
     * code = 500, message = Internal Server Error
     * code = 400, message = Bad Request
     */
    @RequestMapping(value = Constants.REQUEST_MAPPING_PATH + "/addsupplier", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> addSupplier(@RequestBody Profile supplierData);

    /**
     *
     * @return PersonData
     */
    @RequestMapping(value = Constants.REQUEST_MAPPING_PATH + "/getperson", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileData> getperson();

    /**
     *
     * @return PersonData
     */
    @RequestMapping(value = Constants.REQUEST_MAPPING_PATH + "/getcompany", method = RequestMethod.POST, consumes =
            MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileData> getcompany();
}
