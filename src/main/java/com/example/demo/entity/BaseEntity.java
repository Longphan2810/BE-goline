package com.example.demo.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter @Setter @MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    Date createdAt;
    @LastModifiedDate
    Date updatedAt;
}
