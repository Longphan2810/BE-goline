package com.example.demo.mapper;

import com.example.demo.entity.Room;
import com.example.demo.request.RoomRequest;
import com.example.demo.respone.RoomReponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(target = "idApartment",source = "apartment.idApartment")
    RoomReponse toRoomReponse(Room room);

    Room toRoom(RoomRequest roomRequest);
}
