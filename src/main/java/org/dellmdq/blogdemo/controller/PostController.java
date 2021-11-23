package org.dellmdq.blogdemo.controller;


import com.monitorjbl.json.JsonResult;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.Match;
import org.dellmdq.blogdemo.entity.Post;
import org.dellmdq.blogdemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.dellmdq.blogdemo.util.ImageValidation.urlValidation;
import static org.dellmdq.blogdemo.util.ImageValidation.validateJpgPngExtension;

import java.util.List;




@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAll(){

        JsonResult json = JsonResult.instance();
        List<Post> postList = postService.getAll(null);
        String[] excludedProps = {"message","deleteAt","user"};

        return json.use(JsonView.with(postList)
                        .onClass(Post.class, Match.match()
                                .exclude(excludedProps)))
                .returnValue();
    }

    @GetMapping("/{postId}")
    public Post getById(@PathVariable int postId){
        return postService.getById(postId);
    }

    @PostMapping
    public Post add(@RequestBody Post post){
//        //validar imagen del post aca
//        if(urlValidation(post.getImage())) System.out.println(ImageValidation.urlValidation(post.getImage()));
//        if(validateJpgPngExtension(post.getImage())) System.out.println(ImageValidation.validateJpgPngExtension(post.getImage()));
//
//        //if(existencia y (finaliza .jpg || . png) y notNull)

        if(post.getImage() != null && urlValidation(post.getImage()) && validateJpgPngExtension(post.getImage())){
           return postService.add(post);
        }
        else{
            System.out.println("Image Validation Failed");
        }
        return null;
    }

    @GetMapping(params = "title")
    Post getPostsByTitle(@RequestParam("title") String title){
        return postService.getByTitle(title);
    }

    @GetMapping(params = "category")
    public List<Post> getPostsByCategoryName(@RequestParam("category") String category){
        return postService.getByCategoryName(category);
    }

    @GetMapping(params = {"title","category"})
    public Post getByTitleAndCategoryName(@RequestParam("title") String title,
                                            @RequestParam("category") String category){
        return postService.getByTitleAndCategoryName(title, category);
    }

    @DeleteMapping("/{id}")
    public Post softDelete(@PathVariable int id){
        return postService.softDelete(id);
    }
}
