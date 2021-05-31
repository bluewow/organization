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

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrganizationGetSearchService {

    private final GroupsRepository groupsRepository;
    
    public ResponseDto get(RequestDto dto) {
        Set<Groups> set = new LinkedHashSet<>();

        //검색어 관련 Groups 를 가져온다
        List<Groups> rawGroups = groupsRepository.findByNameContains(dto.getSearchKeyword());
        List<Groups> groupsList = rawGroups.stream()
                .filter(g -> g.getType() != GroupType.Member)
                .collect(Collectors.toList());

        //reverseRecursive 를 통하여 set 에 저장한다
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

            return;
        }
        if(groups.getType() != GroupType.Member)
            set.add(groups);

        reverseRecursive(groups.getParent(), set);
    }
}
