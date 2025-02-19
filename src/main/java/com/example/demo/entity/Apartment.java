package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Apartment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idApartment;
    private String name;
    private String address;
    private String image;
    private Boolean isAvailable;
    private Boolean isTheWholeHouse;
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal priceTheWholeHouse;
    private Double roomArea;
    @JsonIgnore
    @OneToMany(mappedBy = "apartment")
    List<Room> listRoom;

}
