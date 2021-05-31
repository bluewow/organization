package com.daou.demo.organization.controller;

import com.daou.demo.organization.controller.dto.RequestDto;
import com.daou.demo.organization.controller.dto.ResponseDto;
import com.daou.demo.organization.service.OrganizationService;
import com.daou.demo.organization.util.BusinessException;
import com.daou.demo.organization.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/organizations")
    public ResponseDto getOrganizations(RequestDto dto) {
        ResponseDto responseDto;

        if(searchKeywordIsNotNull(dto)) {
            responseDto = organizationService.getOrganizationsBySearch(dto);
        } else {
            responseDto = organizationService.getOrganizations(dto);
        }

        return responseDto;
    }

    private boolean searchKeywordIsNotNull(RequestDto dto) {
        return dto.getSearchKeyword() != null;
    }
}
