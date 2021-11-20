package org.dellmdq.blogdemo.service;

import org.dellmdq.blogdemo.entity.Category;
import org.dellmdq.blogdemo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Category save(Category category){
        return categoryRepository.save(category);
    }


}
