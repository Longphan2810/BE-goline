package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Room extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRoom;
    private String name;
    private String image;
    private Boolean isAvailable;
    private Double roomArea;
    private BigDecimal priceRoomDay;
    private BigDecimal priceRoomMonth;
    private String description;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_apartment")
    Apartment apartment;
}
