package com.service;

import com.model.Blog;
import com.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService extends IService<Blog> {
    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findByTitleContaining(Pageable pageable, String searchText);

    Page<Blog> findByCategory(Pageable pageable, Category category);

    Iterable<Blog> findByCategory(Category category);

    Iterable<Blog> findByTitleContaining(String searchText);
}
