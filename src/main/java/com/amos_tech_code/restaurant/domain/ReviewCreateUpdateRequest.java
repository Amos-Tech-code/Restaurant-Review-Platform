package com.amos_tech_code.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCreateUpdateRequest {
    private String content;
    private Integer rating;
    private List<String> photoIds;
}
