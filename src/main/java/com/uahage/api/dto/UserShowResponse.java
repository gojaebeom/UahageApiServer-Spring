package com.uahage.api.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;

@Getter
@ToString
public class UserShowResponse {
    private Long id;
    private String nickname;
    private Short ageGroupType;
    private List<HashMap<String, Object>> babies;
    private HashMap<String, Object> image;
}
