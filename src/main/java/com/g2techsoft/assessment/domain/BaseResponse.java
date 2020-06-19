package com.g2techsoft.assessment.domain;

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
