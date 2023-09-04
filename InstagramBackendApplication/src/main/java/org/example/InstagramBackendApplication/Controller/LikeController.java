package org.example.InstagramBackendApplication.Controller;

import org.example.InstagramBackendApplication.Model.Likes;
import org.example.InstagramBackendApplication.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LikeController {

    @Autowired
    LikeService likeService;

    @PostMapping("Like")
    public String addLike(@RequestBody Likes like){
        return likeService.addLike(like);
    }

    @DeleteMapping("Like")
    public String removeLike(@RequestBody Likes like){
        return likeService.removeLike(like);
    }



    @GetMapping("Likes")
    public List<Likes>getAllLikes(Integer pageNumber, Integer pageSize){
        return likeService.getAllLikes(pageNumber,pageSize);
    }
}
