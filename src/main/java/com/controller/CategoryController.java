package com.controller;

import com.model.Category;
import com.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/create";
    }
    @PostMapping("save")
    public String save(Category category, RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "New category added successfully");
        return "redirect:/categories";
    }

    @GetMapping("/{id}/view")
    public String showView(@PathVariable Long id, Model model) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        Category category = categoryOptional.get();
        model.addAttribute("category", category);
        return "category/view";
    }

    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        Category category = categoryOptional.get();
        model.addAttribute("category", category);
        return "category/update";
    }
    @PostMapping("/update")
    public String update(Category category, RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "Category updated successfully");
        return "redirect:/categories";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        categoryService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Category deleted");
        return "redirect:/categories";
    }
}
