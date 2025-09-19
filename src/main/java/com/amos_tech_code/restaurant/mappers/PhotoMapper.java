package com.amos_tech_code.restaurant.mappers;

import com.amos_tech_code.restaurant.domain.dtos.PhotoDto;
import com.amos_tech_code.restaurant.domain.entities.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {

    PhotoDto toDto(Photo photo);

}
