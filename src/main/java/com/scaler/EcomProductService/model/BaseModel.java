package com.scaler.EcomProductService.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(generator="uuid Generator")
    @GenericGenerator(name="uuid Generator",strategy = "uuid2")
    @Column(name="id", nullable = false,updatable = false)
    private UUID id;
}
