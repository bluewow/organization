package com.daou.demo.organization.controller;

import com.daou.demo.organization.controller.dto.RequestDto;
import com.daou.demo.organization.controller.dto.ResponseDto;
import com.daou.demo.organization.service.OrganizationGetSearchService;
import com.daou.demo.organization.service.OrganizationGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class OrganizationController {

    private final OrganizationGetService organizationGetService;
    private final OrganizationGetSearchService organizationGetSearchService;

    @GetMapping("/organizations")
    public ResponseDto getOrganizations(RequestDto dto) {
        ResponseDto responseDto;

        if(hasSearchKeyword(dto)) {
            responseDto = organizationGetSearchService.get(dto);
        } else {
            responseDto = organizationGetService.get(dto);
        }

        return responseDto;
    }

    private boolean hasSearchKeyword(RequestDto dto) {
        return dto.getSearchKeyword() != null;
    }
}
