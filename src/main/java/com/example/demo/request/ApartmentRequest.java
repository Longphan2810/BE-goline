package com.example.demo.request;

import com.example.demo.entity.Room;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
@AllArgsConstructor @NoArgsConstructor
@Data @Builder
public class ApartmentRequest {


    @NotBlank(message = "Name_Not_Blank")
    private String name;
    @NotBlank(message = "Address_Not_Blank")
    private String address;
    private String image;
    @NotNull(message = "Available_not_null")
    private Boolean isAvailable;
    @NotNull(message = "The_Whole_House_not_null")
    private Boolean isTheWholeHouse;
    private BigDecimal priceTheWholeHouse;
    private Double roomArea;
    private MultipartFile imageFile;

}
