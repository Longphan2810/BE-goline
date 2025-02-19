package com.example.demo.mapper;

import com.example.demo.entity.Apartment;
import com.example.demo.request.ApartmentRequest;
import com.example.demo.respone.ApartmentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {

    ApartmentResponse toApartmentResponse(Apartment apartment);
    Apartment toApartment(ApartmentRequest apartmentRequest);
}
