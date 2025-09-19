package com.amos_tech_code.restaurant.controllers;

import com.amos_tech_code.restaurant.domain.RestaurantCreateUpdateRequest;
import com.amos_tech_code.restaurant.domain.dtos.RestaurantCreateUpdateRequestDto;
import com.amos_tech_code.restaurant.domain.dtos.RestaurantDto;
import com.amos_tech_code.restaurant.domain.dtos.RestaurantSummaryDto;
import com.amos_tech_code.restaurant.domain.entities.Restaurant;
import com.amos_tech_code.restaurant.mappers.RestaurantMapper;
import com.amos_tech_code.restaurant.services.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @PostMapping
    public ResponseEntity<RestaurantDto> createRequest(
            @Valid
            @RequestBody
            RestaurantCreateUpdateRequestDto request
    ) {
        RestaurantCreateUpdateRequest restaurantCreateUpdateRequest = restaurantMapper
                .toRestaurantCreateUpdateRequest(request);

        Restaurant restaurant = restaurantService.createRestaurant(restaurantCreateUpdateRequest);
        RestaurantDto createdRestaurantDto = restaurantMapper.toRestaurantDto(restaurant);

        return ResponseEntity.ok(createdRestaurantDto);
    }

    @GetMapping
    public Page<RestaurantSummaryDto> searchRestaurants(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Float minRating,
            @RequestParam(required = false) Float latitude,
            @RequestParam(required = false) Float longitude,
            @RequestParam(required = false) Float radius,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Page<Restaurant> searchResults = restaurantService.searchRestaurants(
                q, minRating, latitude, longitude, radius, PageRequest.of(page - 1, size)
        );

        return searchResults.map(restaurantMapper::toSummaryDto);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RestaurantDto> getRestaurant(
            @PathVariable("id") String restaurantId
    ) {
        return restaurantService.getRestaurant(restaurantId)
                .map(restaurant -> ResponseEntity.ok(restaurantMapper.toRestaurantDto(restaurant)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(
            @PathVariable("id") String restaurantId,
            @Valid @RequestBody RestaurantCreateUpdateRequestDto requestDto
    ) {
        RestaurantCreateUpdateRequest request = restaurantMapper.toRestaurantCreateUpdateRequest(requestDto);

        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurantId, request);

        return ResponseEntity.ok(restaurantMapper.toRestaurantDto(updatedRestaurant));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteRestaurant(
            @PathVariable("id") String id
    ) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
