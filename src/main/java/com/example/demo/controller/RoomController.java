package com.example.demo.controller;

import com.example.demo.request.RoomRequest;
import com.example.demo.respone.ApiRespone;
import com.example.demo.service.impl.RoomServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("api/rooms")
public class RoomController {

    @Autowired
    private RoomServiceImpl roomServiceImpl;

    @GetMapping("{id}")
    public ApiRespone<?>  getById(@PathVariable("id") Integer id) {
        return ApiRespone
                .builder()
                .result(roomServiceImpl.getById(id))
                .build();
    }

    @GetMapping("/filter")
    public ApiRespone<?> filterApartment(
            @RequestParam(required = false) Boolean isAvailable,
            @RequestParam(required = false) String nameOrAddress,
            @RequestParam Integer idApartment,
            @RequestParam(defaultValue = "true") boolean isSortLowToHigh,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer sizePage){
        return ApiRespone
                .builder()
                .result(roomServiceImpl.filterPage(isAvailable,isSortLowToHigh,nameOrAddress,idApartment,page,sizePage))
                .build();
    }

    @PostMapping
    public ApiRespone<?> postRoom(@ModelAttribute @Valid RoomRequest roomRequest) {
        return  ApiRespone
                .builder()
                .result(roomServiceImpl.save(roomRequest))
                .build();
    }

    @PatchMapping("/{id}")
    public ApiRespone<?> pacthRoom(@PathVariable("id") int id,
                                      @RequestParam(required = false) Map<String,Object> update,
                                      @RequestParam(required = false) MultipartFile imageFile){
        return ApiRespone
                .builder()
                .result(roomServiceImpl.update(id,update,imageFile))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiRespone<?> deleteRoom(@PathVariable("id") Integer id){
        roomServiceImpl.deleteRooms(id);
        return ApiRespone
                .builder()
                .build();
    }

}
