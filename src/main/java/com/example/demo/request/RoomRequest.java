package com.example.demo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data @Getter
@AllArgsConstructor @NoArgsConstructor @Builder
public class RoomRequest {
    @NotBlank(message = "Name_Room_Not_Blank")
    private String name;
    @NotNull(message = "Available_Room_Not_null")
    private Boolean isAvailable;
    private Double roomArea;
    private BigDecimal priceRoomDay;
    private BigDecimal priceRoomMonth;
    @NotNull(message = "Id_Apartment_Not_null")
    private Integer idApartment;
    private MultipartFile imageFile;
    private String description;
}
