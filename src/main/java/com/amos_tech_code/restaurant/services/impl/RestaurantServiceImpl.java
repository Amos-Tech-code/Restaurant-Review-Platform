package com.amos_tech_code.restaurant.services.impl;

import com.amos_tech_code.restaurant.domain.GeoLocation;
import com.amos_tech_code.restaurant.domain.RestaurantCreateUpdateRequest;
import com.amos_tech_code.restaurant.domain.entities.Address;
import com.amos_tech_code.restaurant.domain.entities.Photo;
import com.amos_tech_code.restaurant.domain.entities.Restaurant;
import com.amos_tech_code.restaurant.exceptions.RestaurantNotFoundException;
import com.amos_tech_code.restaurant.repositories.RestaurantRepository;
import com.amos_tech_code.restaurant.services.GeoLocationService;
import com.amos_tech_code.restaurant.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final GeoLocationService geoLocationService;


    @Override
    public Restaurant createRestaurant(RestaurantCreateUpdateRequest request) {
        Address address = request.getAddress();
        GeoLocation geoLocation = geoLocationService.geoLocate(request.getAddress());
        GeoPoint geoPoint = new GeoPoint(geoLocation.getLatitude(), geoLocation.getLatitude());

        List<String> photoIds = request.getPhotoIds();
        List<Photo> photos = photoIds.stream().map(photoUrl -> Photo.builder()
                .url(photoUrl)
                .uploadDate(LocalDateTime.now())
                .build()).toList();

        Restaurant restaurant = Restaurant.builder()
                .name(request.getName())
                .cuisineType(request.getCuisineType())
                .contactInformation(request.getContactInformation())
                .address(request.getAddress())
                .geoLocation(geoPoint)
                .operatingHours(request.getOperatingHours())
                .averageRating(0f)
                .photos(photos)
                .build();

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Page<Restaurant> searchRestaurants(
            String query, Float minRating, Float latitude,
            Float longitude, Float radiusKm, Pageable pageable
    ) {
        if (minRating != null && (query == null || query.isEmpty())) {
            return restaurantRepository.findByAverageRatingGreaterThanEqual(minRating, pageable);
        }

        Float searchMinRating = null == minRating ? 0f : minRating;

        if (query != null && !query.trim().isEmpty()) {
            return restaurantRepository.findByQueryAndMinRating(query, searchMinRating, pageable);
        }

        if (latitude != null && longitude != null && radiusKm != null) {
            return restaurantRepository.findByLocationNear(latitude, longitude, radiusKm, pageable);
        }

        return restaurantRepository.findAll(pageable);
    }

    @Override
    public Optional<Restaurant> getRestaurant(String id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant updateRestaurant(String id, RestaurantCreateUpdateRequest request) {
        Restaurant restaurant = getRestaurant(id)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant does not exist with ID: " + id));

        GeoLocation newGeolocation = geoLocationService.geoLocate(
                request.getAddress()
        );

        GeoPoint newGeopoint = new GeoPoint(newGeolocation.getLatitude(), newGeolocation.getLongitude());
        List<String> photoIds = request.getPhotoIds();
        List<Photo> photos = photoIds.stream().map(photoUrl -> Photo.builder()
                .url(photoUrl)
                .uploadDate(LocalDateTime.now())
                .build()).toList();

        restaurant.setName(request.getName());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setContactInformation(request.getContactInformation());
        restaurant.setAddress(request.getAddress());
        restaurant.setGeoLocation(newGeopoint);
        restaurant.setOperatingHours(request.getOperatingHours());
        restaurant.setAverageRating(0f);
        restaurant.setPhotos(photos);

        return restaurantRepository.save(restaurant);

    }

    @Override
    public void deleteRestaurant(String id) {
        restaurantRepository.deleteById(id);
    }
}
