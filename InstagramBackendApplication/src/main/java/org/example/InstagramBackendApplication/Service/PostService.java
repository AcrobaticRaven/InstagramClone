package org.example.InstagramBackendApplication.Service;

import org.example.InstagramBackendApplication.Model.Post;
import org.example.InstagramBackendApplication.Repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public String deletePost(Integer Id){
        Optional<Post>postOptional = postRepo.findById(Id);
        if(postOptional.isPresent()){
            Post foundPost = postOptional.get();
            postRepo.delete(foundPost);
            return "Post has been deleted";
        }else{
            return "Post not found of Id : " + Id;
        }
    }

    public String updatePost(Post post, Integer Id){
        Post connectedPost = postRepo.findById(post.getPostId()).orElse(null);
        if(!(connectedPost==null) && connectedPost.getPostOwner().getUserId().equals(Id)){
            connectedPost.setPostData(post.getPostData());
            postRepo.save(connectedPost);
            return "Post has been updated";
        }else if(connectedPost==null){
            return "Post not found for Id : " + Id;
        }else{
            return "Unauthorised user request detected .... request denied";
        }
    }
}
