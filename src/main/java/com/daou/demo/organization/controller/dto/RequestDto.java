package com.daou.demo.organization.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private String deptCode;
    private Boolean deptOnly;
    private String searchType;
    private String searchKeyword;
}
