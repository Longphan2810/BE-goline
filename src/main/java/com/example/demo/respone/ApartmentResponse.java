package com.example.demo.respone;

import com.example.demo.entity.Room;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Data @Builder
public class ApartmentResponse {
    private Integer idApartment;
    private String name;
    private String address;
    private String image;
    private Boolean isAvailable;
    private Boolean isTheWholeHouse;
    private BigDecimal priceTheWholeHouse;
    private Double roomArea;
    List<Room> listRoom;

}
