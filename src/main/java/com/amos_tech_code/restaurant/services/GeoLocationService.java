package com.amos_tech_code.restaurant.services;

import com.amos_tech_code.restaurant.domain.GeoLocation;
import com.amos_tech_code.restaurant.domain.entities.Address;

public interface GeoLocationService {

    GeoLocation geoLocate(Address address);

}
