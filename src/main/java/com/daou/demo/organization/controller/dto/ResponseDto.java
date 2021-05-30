package com.daou.demo.organization.controller.dto;

import com.daou.demo.organization.domain.GroupType;
import com.daou.demo.organization.domain.Groups;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseDto {
    private Long id;
    private GroupType type;
    private String name;
    private String code;
    private List<ResponseDto> children = new ArrayList<>();

    private ResponseDto(Long id, GroupType type, String name, String code) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.code = code;
    }

    public static ResponseDto newInstance(Groups groups) {
        return new ResponseDto(groups.getId(), groups.getType(), groups.getName(), groups.getDeptCode());
    }
}
