package com.daou.demo.organization.service;

import com.daou.demo.organization.controller.dto.RequestDto;
import com.daou.demo.organization.controller.dto.ResponseDto;
import com.daou.demo.organization.domain.GroupType;
import com.daou.demo.organization.domain.Groups;
import com.daou.demo.organization.repository.GroupsRepository;
import com.daou.demo.organization.util.BusinessException;
import com.daou.demo.organization.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
@RequiredArgsConstructor
@Service
public class OrganizationGetService {

    private final GroupsRepository groupsRepository;

    public ResponseDto get(RequestDto dto) {
        ResponseDto responseDto;

        if(deptCodeIsNull(dto)) {
            Groups groups = groupsRepository.findRootByParentIdIsNull()
                    .orElseThrow(() -> new BusinessException(ErrorCode.ERROR_CODE_002));
            responseDto = ResponseDto.newInstance(groups);
            recursive(groups, dto.getDeptOnly(), responseDto);
        } else {
            Groups groups = groupsRepository.findByDeptCode(dto.getDeptCode())
                    .orElseThrow(() -> new BusinessException(ErrorCode.ERROR_CODE_001));
            responseDto = ResponseDto.newInstance(groups);
            recursive(groups, dto.getDeptOnly(), responseDto);
        }

        return responseDto;
    }

    private boolean deptCodeIsNull(RequestDto dto) {
        return dto.getDeptCode() == null;
    }

    private void recursive(Groups groups, boolean departmentOnly, ResponseDto responseDto) {
        if(groups == null) return;
        if(isDepartment(departmentOnly, groups)) {
            //responseDto 완전탐색을 통해 데이터를 밀어넣는다
            responseDtoUpdate(responseDto, groups);
        }

        for(Groups children : groups.getChildren()) {
            if(children.getParent() != null) {
                recursive(children, departmentOnly, responseDto);
            }
        }
    }

    private Boolean isDepartment(Boolean departmentOnly, Groups g) {
        if(departmentOnly) {
            if(g.getType() == GroupType.Member)
                return false;
            else
                return true;
        }
        return true;
    }

    private void responseDtoUpdate(ResponseDto responseDto, Groups groups) {
        if(responseDto == null) return;
        if(groups.getParent() == null)
            return;

        if(responseDto.getId() == groups.getParent().getId())
            responseDto.getChildren().add(ResponseDto.newInstance(groups));

        for(ResponseDto children : responseDto.getChildren()) {
            if(children.getId() != null) {
                responseDtoUpdate(children, groups);
            }
        }
    }
}
