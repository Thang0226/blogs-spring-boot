package com.repo;

import com.model.Blog;
import com.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Long> {
    Page<Blog> findByTitleContaining(Pageable pageable, String searchText);

    Page<Blog> findByCategory(Pageable pageable, Category category);

    Iterable<Blog> findByCategory(Category category);

    Iterable<Blog> findByTitleContaining(String searchText);
}
