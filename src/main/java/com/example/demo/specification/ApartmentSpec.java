package com.example.demo.specification;

import com.example.demo.entity.Apartment;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;



public class ApartmentSpec {

    public  static Specification<Apartment> isTheWholeHouse(Boolean isTheWholeHouseFlat) {

        return ((root, query, criteriaBuilder) ->
            isTheWholeHouseFlat == null ? null : criteriaBuilder.equal(root.get("isTheWholeHouse"), isTheWholeHouseFlat)
        );
    }

    public  static Specification<Apartment> nameOrAddressLike(String nameOrAddress) {

        return ((root, query, criteriaBuilder) ->{
            if(nameOrAddress==null) return null;
            Predicate namePredicate =  criteriaBuilder.like(root.get("name"), "%" + nameOrAddress + "%");
            Predicate addressPredicate =  criteriaBuilder.like(root.get("address"), "%" + nameOrAddress + "%");
            return criteriaBuilder.or(namePredicate, addressPredicate);
        }  );
    }

    public  static Specification<Apartment> isAvailable(Boolean isAvailableFlat) {

        return ((root, query, criteriaBuilder) ->
                isAvailableFlat == null ? null : criteriaBuilder.equal(root.get("isAvailable"), isAvailableFlat)
        );
    }

    public  static Specification<Apartment> isSortLowToHigh(Boolean isSortLowToHigh) {

        return ((root, query, criteriaBuilder) ->
                isSortLowToHigh == null ? null : criteriaBuilder.equal(root.get("isSortLowToHigh"), isSortLowToHigh)
        );
    }



}
