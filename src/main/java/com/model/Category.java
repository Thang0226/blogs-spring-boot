package com.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
