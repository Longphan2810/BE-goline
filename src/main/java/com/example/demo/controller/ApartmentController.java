package com.example.demo.controller;

import com.example.demo.request.ApartmentRequest;
import com.example.demo.respone.ApartmentResponse;
import com.example.demo.respone.ApiRespone;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.impl.ApartmentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("api/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentServiceImpl apartmentServiceImpl;

    @GetMapping
    public ApiRespone<?> getApartment(){
        return ApiRespone
                .builder()
                .result(apartmentServiceImpl.getAll())
                .build();
    }
    @GetMapping("/filter")
    public ApiRespone<?> filterApartment(
                            @RequestParam(required = false) Boolean isTheWholeHouse,
                            @RequestParam(required = false) Boolean isAvailable,
                            @RequestParam(required = false) String nameOrAddress,
                            @RequestParam(defaultValue = "true") boolean isSortLowToHigh,
                            @RequestParam(defaultValue = "0") Integer page,
                            @RequestParam(defaultValue = "3") Integer sizePage){
        return ApiRespone
                .builder()
                .result(apartmentServiceImpl.filterPage(isTheWholeHouse, isAvailable, isSortLowToHigh,nameOrAddress, page, sizePage))
                .build();
    }

    @GetMapping("/search")
    public ApiRespone<?> searchApartment(){
        return ApiRespone
                .builder()
                .result(apartmentServiceImpl.getAll())
                .build();
    }

    @GetMapping("/{id}")
    public ApiRespone<?> getApartmentById(@PathVariable("id") int id){
        return ApiRespone
                .builder()
                .result(apartmentServiceImpl.getById(id))
                .build();
    }

    @PostMapping
    public ApiRespone<?> postApartment(@ModelAttribute @Valid ApartmentRequest apartmentRequest){
        return ApiRespone
                .builder()
                .result(apartmentServiceImpl.save(apartmentRequest))
                .build();
    }
    @PatchMapping("/{id}")
    public ApiRespone<?> putApartment(@PathVariable("id") int id,
                                      @RequestParam(required = false) Map<String,Object> update,
                                      @RequestParam(required = false) MultipartFile imageFile){
        return ApiRespone
                .builder()
                .result(apartmentServiceImpl.update(id,update,imageFile))
                .build();
    }
}
