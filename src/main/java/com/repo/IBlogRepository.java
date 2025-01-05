package com.repo;

import com.model.Blog;
import com.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Long> {
    @Query(value = "from Blog where title like %:text% and disabled = false")
    Page<Blog> findByTitleContaining(Pageable pageable, @Param("text") String searchText);

    @Query(value = "from Blog where category = :cat and disabled = false")
    Page<Blog> findByCategory(Pageable pageable, @Param("cat") Category category);

//    Iterable<Blog> findByCategory(Category category);
//
//    Iterable<Blog> findByTitleContaining(String searchText);

    Page<Blog> findAllByDisabledEquals(Boolean disabled, Pageable pageable);
}
