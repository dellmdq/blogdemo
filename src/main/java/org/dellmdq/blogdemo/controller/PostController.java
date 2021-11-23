package org.dellmdq.blogdemo.controller;


import com.monitorjbl.json.JsonResult;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.Match;
import org.dellmdq.blogdemo.entity.Post;
import org.dellmdq.blogdemo.entity.User;
import org.dellmdq.blogdemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import static org.dellmdq.blogdemo.util.ImageValidation.urlValidation;
import static org.dellmdq.blogdemo.util.ImageValidation.validateJpgPngExtension;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


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

    //taken from https://stackoverflow.com/questions/29988841/spring-rest-and-patch-method
    @PatchMapping("/{id}")
    ResponseEntity<Post> patch(@PathVariable int id, @RequestBody Map<String, Object> fields){

        // Sanitize and validate the data
        if (id <= 0 || fields == null || fields.isEmpty() || !fields.get("id").equals(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Invalid claim object received or invalid id or id does not match object
        }

        Post post = postService.getById(id);

        // Does the object exist?
        if(post == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Claim object does not exist
        }

        // Remove id from request, we don't ever want to change the id.
        // This is not necessary, you can just do it to save time on the reflection
        // loop used below since we checked the id above
        fields.remove("id");

        fields.forEach((k, v) -> {
            // use reflection to get field k on object and set it to value v
            // Change Claim.class to whatver your object is: Object.class
            Field field = ReflectionUtils.findField(Post.class, k); // find field in the object class
            field.setAccessible(true);
            ReflectionUtils.setField(field, post, v); // set given field for defined object to value V
        });

        postService.save(post);
        return new ResponseEntity<>(post, HttpStatus.OK);

    }
}
