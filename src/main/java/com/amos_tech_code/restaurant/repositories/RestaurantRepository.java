package com.amos_tech_code.restaurant.repositories;

import com.amos_tech_code.restaurant.domain.entities.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, String> {

    Page<Restaurant> findByAverageRatingGreaterThanEqual(Float minRating, Pageable pageable);

    @Query("{" +
            " \"bool\": {" +
            "   \"must\": [" +
            "      {\"range\": {\"averageRating\": {\"gte\": ?1}}}" +
            "    ]," +
            "}")
    Page<Restaurant> findByQueryAndMinRating(String query, Float minRating, Pageable pageable);

    @Query()
    Page<Restaurant> findByLocationNear(
            Float latitude,
            Float longitude,
            Float radiusKm,
            Pageable pageable
    );
}
