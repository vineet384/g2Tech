package com.daimler.travelbook.roadtrip.domain;

import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class BaseResponse {


    private String message;
    private int status;
    private List<Object> resObject;
}
