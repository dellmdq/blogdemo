package org.dellmdq.blogdemo.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.dellmdq.blogdemo.entity.Post;
import org.dellmdq.blogdemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

//    @GetMapping
//    public List<Post> getAll(){
//        List<Post> postList = postService.getAll();
//        System.out.println(postList);
//        return postList;
//    }

    @GetMapping()
    public MappingJacksonValue getAllTEST(){
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("message", "deleteAt", "user");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("postFilter", simpleBeanPropertyFilter);

        List<Post> postList = postService.getAll();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(postList);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("/{postId}")
    public Post getById(@PathVariable int postId){
        return postService.getById(postId);
    }

    @PostMapping
    public Post add(@RequestBody Post post){
        return postService.add(post);
    }

    @GetMapping(params = "{title}")
    Post getPostsByTitle(@RequestParam("title") String title){
        return postService.getByTitle(title);
    }

    @GetMapping(params = "{category}")
    public Post getPostsByCategoryName(@RequestParam("category") String category){
        return postService.getByCategoryName(category);
    }

    @GetMapping(params = {"title","category"})
    public Post getByTitleAndCategoryName(@RequestParam("title") String title,
                                            @RequestParam("category") String category){
        return postService.getByTitleAndCategoryName(title, category);
    }


}
