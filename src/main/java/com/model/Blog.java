package com.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "blog")
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author;
    private String imageFile;
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Blog() {
    }

    public Blog(Long id, String title, String content, String author, String imageFile, LocalDateTime time, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.imageFile = imageFile;
        this.time = time;
        this.category = category;
    }
}
