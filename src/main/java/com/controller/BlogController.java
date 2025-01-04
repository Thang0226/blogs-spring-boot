package com.controller;

import com.model.Blog;
import com.model.BlogForm;
import com.model.Category;
import com.service.IBlogService;
import com.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    private final IBlogService blogService;
    private final ICategoryService categoryService;

    @Autowired
    public BlogController(IBlogService blogService, ICategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listBlogs(@PageableDefault(size = 2, sort = "time", direction = Sort.Direction.ASC) Pageable pageable,
                            @RequestParam("search") Optional<String> search,
                            @RequestParam("category_id") Optional<Long> cat_id, Model model) {
        Page<Blog> blogs;
        if(search.isPresent()){
            blogs = blogService.findByTitleContaining(pageable, search.get());
        } else if (cat_id.isPresent()){
            Long id = cat_id.get();
            Optional<Category> category = categoryService.findById(id);
            blogs = blogService.findByCategory(pageable, category.get());
        } else {
            blogs = blogService.findAll(pageable);
        }
        model.addAttribute("blogs", blogs);
        model.addAttribute("categories", categoryService.findAll());
        return "blog/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blogForm", new BlogForm());
        model.addAttribute("categories", categoryService.findAll());
        return "blog/create";
    }

    @Value("${file_upload}")
    private String folderPath;

    @PostMapping("/save")
    public String saveBlog(BlogForm blogForm, RedirectAttributes redirectAttributes) {
        Optional<Category> category = categoryService.findById(blogForm.getCategory_id());
        MultipartFile multipartFile = blogForm.getImageFile();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(blogForm.getImageFile().getBytes(), new File(folderPath + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        Blog blog = new Blog(blogForm.getId(), blogForm.getTitle(), blogForm.getContent(), blogForm.getAuthor(),
                fileName, LocalDateTime.now(), category.get());
        blogService.save(blog);

        redirectAttributes.addFlashAttribute("message", "New blog added successfully");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/view")
    public String showView(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        model.addAttribute("blog", blog.get());
        return "blog/view";
    }

    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Blog> blogOptional = blogService.findById(id);
        Blog blog = blogOptional.get();
        model.addAttribute("image", blog.getImageFile());
        BlogForm blogForm = new BlogForm(id, blog.getTitle(), blog.getContent(), blog.getAuthor(),
                null, blog.getTime().toString(), blog.getCategory().getId());
        model.addAttribute("blogForm", blogForm);
        model.addAttribute("categories", categoryService.findAll());
        return "blog/update";
    }

    @PostMapping("/update")
    public String updateBlog(BlogForm blogForm, RedirectAttributes redirectAttributes) {
        Optional<Blog> blogOptional = blogService.findById(blogForm.getId());
        Blog blog = blogOptional.get();
        MultipartFile multipartFile = blogForm.getImageFile();
        if (!Objects.requireNonNull(multipartFile.getOriginalFilename()).isEmpty()) {
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(blogForm.getImageFile().getBytes(), new File(folderPath + fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
            blog.setImageFile(fileName);
        }
        blog.setTitle(blogForm.getTitle());
        blog.setContent(blogForm.getContent());
        blog.setAuthor(blogForm.getAuthor());
        blog.setTime(LocalDateTime.now());
        Optional<Category> category = categoryService.findById(blogForm.getCategory_id());
        blog.setCategory(category.get());
        blogService.save(blog);

        redirectAttributes.addFlashAttribute("message", "Blog updated successfully");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        blogService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Blog deleted");
        return "redirect:/blogs";
    }
}
