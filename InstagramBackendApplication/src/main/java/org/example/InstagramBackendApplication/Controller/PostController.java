package org.example.InstagramBackendApplication.Controller;

import org.example.InstagramBackendApplication.Model.Post;
import org.example.InstagramBackendApplication.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    PostService postService;


    @PostMapping("post")
    public String savePost(@RequestBody Post post){
        return postService.savePost(post);
    }

    @GetMapping("post/{Id}")
    public Post getPostById(@PathVariable Integer Id){
        return postService.getPostById(Id);
    }

    @DeleteMapping("Post/{Id}")
    public String deletePost(@PathVariable Integer Id){
        return postService.deletePost(Id);
    }

    @PutMapping("Post/{Id}")
    public String updatePost(@RequestBody Post post, @PathVariable Integer Id){
        return postService.updatePost(post,Id);
    }
}
