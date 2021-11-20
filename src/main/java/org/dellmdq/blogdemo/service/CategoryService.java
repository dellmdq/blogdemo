package org.dellmdq.blogdemo.service;

import org.dellmdq.blogdemo.entity.Category;
import org.dellmdq.blogdemo.entity.User;
import org.dellmdq.blogdemo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id){
        return categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Categoria no encontrada."));
    }

    public Category getCategoryByTitle(String title){
        return categoryRepository.findByTitle(title);
    }

    @Transactional
    public Category save(Category category){
        return categoryRepository.save(category);
    }

    @Transactional
    public Category update(Category category){
        Category existingCategory = categoryRepository.findById(category.getId()).orElseThrow(() -> new NoSuchElementException("Categoria no encontrada."));

        existingCategory.setTitle(category.getTitle());

        return categoryRepository.save(existingCategory);
    }

    @Transactional
    public String delete(int categoryId) {
        categoryRepository.deleteById(categoryId);
        return "Category Id: " + categoryId + " deleted.";
    }
}
