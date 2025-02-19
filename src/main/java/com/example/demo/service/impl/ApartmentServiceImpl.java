package com.example.demo.service.impl;

import com.example.demo.entity.Apartment;
import com.example.demo.mapper.ApartmentMapper;
import com.example.demo.repository.ApartmentRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.request.ApartmentRequest;
import com.example.demo.respone.ApartmentResponse;
import com.example.demo.service.CloudinaryService;
import com.example.demo.specification.ApartmentSpec;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ApartmentMapper apartmentMapper;

    @Autowired
    private CloudinaryService cloudinaryService;

    public List<ApartmentResponse> getAll() {
        return apartmentRepository.findAll()
                .stream()
                .map(apartmentMapper::toApartmentResponse)
                .collect(Collectors.toList());
    }

    public ApartmentResponse getById(Integer id) {
        return apartmentMapper.toApartmentResponse(
                apartmentRepository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("Apartment_not_found")));
    }

    public Page<ApartmentResponse> filterPage(Boolean isTheWholeHouse,
                                              Boolean isAvailable,
                                              Boolean isSortLowToHigh,
                                              String nameOrAddress,
                                              Integer page,
                                              Integer sizePage) {
        if(nameOrAddress!=null){
        if(nameOrAddress.trim().equals("")) {
            nameOrAddress = null;
        }}
        Sort sortPrice = isSortLowToHigh ? Sort.by("priceTheWholeHouse").ascending()
                : Sort.by("priceTheWholeHouse").descending();
        Pageable pageable = PageRequest.of(page, sizePage, sortPrice);
        Specification<Apartment> spec = Specification.where(ApartmentSpec.isTheWholeHouse(isTheWholeHouse)
                .and(ApartmentSpec.isAvailable(isAvailable))
                .and(ApartmentSpec.nameOrAddressLike(nameOrAddress)));
        Page<Apartment> pageApartment = apartmentRepository.findAll(spec, pageable);
        return pageApartment.map(apartmentMapper::toApartmentResponse);
    }

    public ApartmentResponse save(ApartmentRequest request) {
        if (request.getImageFile() != null) {
            try {
                String linkImg = cloudinaryService.uploadImage(request.getImageFile()).get("url").toString();
                request.setImage(linkImg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return apartmentMapper.toApartmentResponse(
                apartmentRepository.save(apartmentMapper.toApartment(request)));

    }

    public ApartmentResponse update(int idApartment, Map<String, Object> updates, MultipartFile imgFile) {
        Apartment apartmentFounded = apartmentRepository
                .findById(idApartment)
                .orElseThrow(() -> new RuntimeException("Apartment_not_found"));

        if (updates.isEmpty()&&imgFile==null) {
            throw new RuntimeException("Nothing_to_update");
        }

        if(imgFile!=null){
                try {
                    String linkImg = cloudinaryService.uploadImage(imgFile).get("url").toString();
                    apartmentFounded.setImage(linkImg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.updateValue(apartmentFounded,updates);

        } catch (JsonMappingException e) {
            System.out.println(e.getMessage());
        }
        return apartmentMapper.toApartmentResponse(
                apartmentRepository.save(apartmentFounded));
    }

}
