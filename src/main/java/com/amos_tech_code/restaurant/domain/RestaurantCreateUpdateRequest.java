package com.amos_tech_code.restaurant.domain;

import com.amos_tech_code.restaurant.domain.entities.Address;
import com.amos_tech_code.restaurant.domain.entities.OperatingHours;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantCreateUpdateRequest {
    private String name;
    private String cuisineType;
    private String contactInformation;
    private Address address;
    private OperatingHours operatingHours;
    private List<String> photoIds;
}
