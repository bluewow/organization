package com.daou.demo.organization.service;

import com.daou.demo.organization.controller.dto.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 조직도 공통 모듈 서비스
 */
@Service
public class OrganizationCommonService {

    public void recursiveSort(ResponseDto responseDto) {
        if(responseDto == null) return;
        Collections.sort(responseDto.getChildren());

        for(ResponseDto children : responseDto.getChildren()) {
            if(children.getChildren() != null)
                recursiveSort(children);
        }
    }

}
