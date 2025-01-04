package com.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BlogForm {
    private Long id;
    private String title;
    private String content;
    private String author;
    private MultipartFile imageFile;
    private String time;
    private Long category_id;

    public BlogForm() {
    }

    public BlogForm(Long id, String title, String content, String author, MultipartFile imageFile, String time, Long category_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.imageFile = imageFile;
        this.time = time;
        this.category_id = category_id;
    }
}
