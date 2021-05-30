package com.daou.demo.organization.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
public class RequestDto {
    private String deptCode;
    private Boolean deptOnly;
    private String searchType;
    private String searchKeyword;

    public RequestDto() {
        this.deptCode = null;
        this.deptOnly = true;
        this.searchType = null;
        this.searchKeyword = null;
    }
}
