package com.example.demo.specification;

import com.example.demo.entity.Apartment;
import com.example.demo.entity.Room;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class RoomSpec {

    public  static Specification<Room> isAvailable(Boolean isAvailableFlat) {

        return ((root, query, criteriaBuilder) ->
                isAvailableFlat == null ? null : criteriaBuilder.equal(root.get("isAvailable"), isAvailableFlat)
        );
    }

    public  static Specification<Room> idApart(Integer idApart) {

        return ((root, query, criteriaBuilder) ->
                 criteriaBuilder.equal(root.get("apartment").get("idApartment"),idApart)
        );
    }


    public  static Specification<Room> nameOrAddressLike(String nameOrAddress) {
        System.out.println(nameOrAddress);
        return ((root, query, criteriaBuilder) ->
            nameOrAddress == null ? null : criteriaBuilder.like(root.get("name"),"%" +nameOrAddress+"%" )
          );
    }
}
