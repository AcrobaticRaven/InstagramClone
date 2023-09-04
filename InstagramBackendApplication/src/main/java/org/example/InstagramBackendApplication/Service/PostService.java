package org.example.InstagramBackendApplication.Service;

import org.example.InstagramBackendApplication.Model.Post;
import org.example.InstagramBackendApplication.Repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;

    public String savePost(Post post ){
        postRepo.save(post);
        return "Post saved";
    }

    public Post getPostById(Integer Id){
        return postRepo.findById(Id).orElse(null);
    }
}
