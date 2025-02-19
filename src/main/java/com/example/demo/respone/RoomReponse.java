package com.example.demo.respone;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor @NoArgsConstructor
@Data @Builder
public class RoomReponse {
    private Integer idRoom;
    private String name;
    private Boolean isAvailable;
    private String image;
    private Double roomArea;
    private BigDecimal priceRoomDay;
    private BigDecimal priceRoomMonth;
    private Integer idApartment;
    private String description;
}
