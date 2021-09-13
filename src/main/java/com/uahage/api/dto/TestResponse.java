package com.uahage.api.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class TestResponse {
    private Long id;
    private String name;
    private List<String> menu;
}
