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

@RequiredArgsConstructor
@Service
public class OrganizationService {

    private final GroupsRepository groupsRepository;

    public ResponseDto getOrganizationsBySearch(RequestDto dto) {
        Set<Groups> set = new HashSet<>();

        //검색어 관련 Groups 를 가져온다
        List<Groups> rawGroups = groupsRepository.findByNameContains(dto.getSearchKeyword());
        List<Groups> groupsList = rawGroups.stream()
                .filter(g -> g.getType() != GroupType.Member)
                .collect(Collectors.toList());

        //reverseRecursive 를 통하여 set 에 순서없이 저장한다
        groupsList.forEach(g -> reverseRecursive(g, set));

        //set data 정렬
        Groups groups = set.stream().filter(g -> g.getParent() == null).findAny().get();
        ResponseDto responseDto = ResponseDto.newInstance(groups);
        set.remove(groups);
        setDataOrdering(responseDto, set);

        return responseDto;
    }

    private void setDataOrdering(ResponseDto responseDto, Set<Groups> set) {
        if(responseDto == null) return;
        Optional<Groups> groups = set.stream().filter(g -> g.getParent().getId() == responseDto.getId()).findAny();
        if(groups.isPresent()) {
            responseDto.getChildren().add(ResponseDto.newInstance(groups.get()));
            set.remove(groups.get());
            setDataOrdering(responseDto, set);
        }

        for (ResponseDto children : responseDto.getChildren()) {
            setDataOrdering(children, set);
        }
    }

    private void reverseRecursive(Groups groups, Set<Groups> set) {
        if(groups.getParent() == null) {
            if(groups.getType() != GroupType.Member)
                set.add(groups); //최상위 노드를 저장하고 재귀를 종료한다
//                set.add(ResponseDto.newInstance(groups)); //최상위 노드를 저장하고 재귀를 종료한다

            return;
        }
        if(groups.getType() != GroupType.Member)
            set.add(groups);
//            set.add(ResponseDto.newInstance(groups));

        reverseRecursive(groups.getParent(), set);
    }

    public ResponseDto getOrganizations(RequestDto dto) {
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
