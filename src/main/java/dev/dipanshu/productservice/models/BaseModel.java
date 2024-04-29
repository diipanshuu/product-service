package dev.dipanshu.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // Generation strategy for PK
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}
