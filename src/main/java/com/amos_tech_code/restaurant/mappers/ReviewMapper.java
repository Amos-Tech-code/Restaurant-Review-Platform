package com.amos_tech_code.restaurant.mappers;

import com.amos_tech_code.restaurant.domain.ReviewCreateUpdateRequest;
import com.amos_tech_code.restaurant.domain.dtos.ReviewCreateUpdateRequestDto;
import com.amos_tech_code.restaurant.domain.dtos.ReviewDto;
import com.amos_tech_code.restaurant.domain.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    ReviewCreateUpdateRequest toReviewCreateUpdateRequest(ReviewCreateUpdateRequestDto dto);

    ReviewDto toDto(Review review);

}
