package com.service.impl;

import com.model.Blog;
import com.model.Category;
import com.repo.IBlogRepository;
import com.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> findByTitleContaining(Pageable pageable, String searchText) {
        return blogRepository.findByTitleContaining(pageable, searchText);
    }

    @Override
    public Page<Blog> findByCategory(Pageable pageable, Category cat) {
        return blogRepository.findByCategory(pageable, cat);
    }

    @Override
    public Iterable<Blog> findByCategory(Category category) {
        return blogRepository.findByCategory(category);
    }

    @Override
    public Iterable<Blog> findByTitleContaining(String searchText) {
        return blogRepository.findByTitleContaining(searchText);
    }
}
