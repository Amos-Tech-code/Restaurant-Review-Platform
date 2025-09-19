package com.amos_tech_code.restaurant.mappers;

import com.amos_tech_code.restaurant.domain.RestaurantCreateUpdateRequest;
import com.amos_tech_code.restaurant.domain.dtos.RestaurantCreateUpdateRequestDto;
import com.amos_tech_code.restaurant.domain.dtos.RestaurantDto;
import com.amos_tech_code.restaurant.domain.dtos.RestaurantSummaryDto;
import com.amos_tech_code.restaurant.domain.entities.Restaurant;
import com.amos_tech_code.restaurant.domain.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {

    RestaurantCreateUpdateRequest toRestaurantCreateUpdateRequest(RestaurantCreateUpdateRequestDto dto);

    @Mapping(source = "reviews", target = "totalReviews", qualifiedByName = "populateTotalReviews")
    RestaurantDto toRestaurantDto(Restaurant restaurant);

    @Mapping(target = "latitude", expression = "java(geoPoint.getLat())")
    @Mapping(target = "longitude", expression = "java(geoPoint.getLon())")
    GeoPoint toGeoPointDto(GeoPoint geoPoint);

    @Mapping(source = "reviews", target = "totalReviews", qualifiedByName = "populateTotalReviews")
    RestaurantSummaryDto toSummaryDto(Restaurant restaurant);

    @Named("populateTotalReviews")
    default Integer populateTotalReviews(List<Review> reviews) {
        return reviews.size();
    }

}
