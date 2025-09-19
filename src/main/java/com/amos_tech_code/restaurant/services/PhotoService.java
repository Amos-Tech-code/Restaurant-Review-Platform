package com.amos_tech_code.restaurant.services;

import com.amos_tech_code.restaurant.domain.entities.Photo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface PhotoService {

    Photo uploadPhoto(MultipartFile file);

    Optional<Resource> getPhotoAsResource(String id);
}
