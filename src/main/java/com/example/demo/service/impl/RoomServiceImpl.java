package com.example.demo.service.impl;

import com.example.demo.entity.Apartment;
import com.example.demo.entity.Room;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.mapper.RoomMapperImpl;
import com.example.demo.repository.ApartmentRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.request.RoomRequest;
import com.example.demo.respone.ApartmentResponse;
import com.example.demo.respone.RoomReponse;
import com.example.demo.service.CloudinaryService;
import com.example.demo.specification.ApartmentSpec;
import com.example.demo.specification.RoomSpec;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

@Service
public class RoomServiceImpl {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private RoomMapperImpl roomMapperImpl;
    @Autowired
    private RoomMapper roomMapper;

    public RoomReponse getById(Integer id) {
        return roomMapperImpl
                .toRoomReponse(roomRepository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("Room_not_found")));
    }

    public RoomReponse save(RoomRequest roomRequest) {
        Apartment apartmentFounded = apartmentRepository
                .findById(roomRequest.getIdApartment())
                .orElseThrow(() -> new RuntimeException("Apartment_not_found"));
        Room roomNeedCreate = roomMapperImpl.toRoom(roomRequest);
        if (roomRequest.getImageFile() != null) {
            try {
                String linkImg = cloudinaryService.uploadImage(roomRequest.getImageFile()).get("url").toString();
                roomNeedCreate.setImage(linkImg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(apartmentFounded.getIdApartment());
        roomNeedCreate.setApartment(apartmentFounded);

        return roomMapperImpl.toRoomReponse(roomRepository.save(roomNeedCreate));
    }

    public RoomReponse update(Integer id, Map<String, Object> updates, MultipartFile imageFile)  {
        Room roomNeedUpdate = roomRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Room_not_found"));

        if (roomNeedUpdate == null && imageFile == null) {
            throw new RuntimeException("Not_thing_to_update_room");
        }

        if (imageFile != null) {
            try {
                String linkImg = cloudinaryService.uploadImage(imageFile).get("url").toString();
                roomNeedUpdate.setImage(linkImg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.updateValue(roomNeedUpdate,updates);

        } catch (JsonMappingException e) {
            System.out.println(e.getMessage());
        }

        return roomMapperImpl.toRoomReponse(roomRepository.save(roomNeedUpdate));
    }

    public Page<RoomReponse> filterPage(Boolean isAvailable,
                                 Boolean isSortLowToHigh,
                                 String nameRoomm,
                                 Integer idApart,
                                 Integer page,
                                 Integer sizePage) {
        if(nameRoomm!=null){
            if(nameRoomm.trim().equals("")) {
                nameRoomm = null;
            }}
        Sort sortPrice = isSortLowToHigh ? Sort.by("priceRoomMonth").ascending()
                : Sort.by("priceRoomMonth").descending();
        Pageable pageable = PageRequest.of(page, sizePage, sortPrice);
        Specification<Room> spec = Specification.where(RoomSpec.isAvailable(isAvailable)
                                                .and(RoomSpec.nameOrAddressLike(nameRoomm))
                                                .and(RoomSpec.idApart(idApart)));
        Page<Room> pageRoom = roomRepository.findAll(spec, pageable);
        return pageRoom.map(roomMapper::toRoomReponse);
    }

    public void deleteRooms(Integer id){
        roomRepository.deleteById(id);
    }

}
