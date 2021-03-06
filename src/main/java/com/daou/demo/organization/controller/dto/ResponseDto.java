package com.daou.demo.organization.controller.dto;

import com.daou.demo.organization.domain.GroupType;
import com.daou.demo.organization.domain.Groups;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
@NoArgsConstructor
@EqualsAndHashCode
public class ResponseDto implements Comparable<ResponseDto>{
    private Integer id;
    private GroupType type;
    private String name;
    private String code;
    private List<ResponseDto> children = new ArrayList<>();

    private ResponseDto(Integer id, GroupType type, String name, String code) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.code = code;
    }

    public static ResponseDto newInstance(Groups groups) {
        return new ResponseDto(groups.getId(), groups.getType(), groups.getName(), groups.getDeptCode());
    }

    @Override
    public int compareTo(ResponseDto responseDto) {
        if(this.getType().getI() < responseDto.getType().getI())
            return 1;
        else if(this.getType().getI() > responseDto.getType().getI())
            return -1;
        return 0;
    }
}
