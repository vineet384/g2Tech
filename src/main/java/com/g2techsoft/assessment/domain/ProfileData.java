package com.g2techsoft.assessment.domain;

import lombok.Data;

import java.util.List;

@Data
public class ProfileData extends BaseResponse {
    private List<PersonData> personDataList;
    private List<CompanyData> companyDataList;
}
