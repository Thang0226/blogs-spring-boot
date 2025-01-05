package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogForm {
    private Long id;
    private String title;
    private String content;
    private String author;
    private MultipartFile imageFile;
    private String time;
    private Long category_id;
}
