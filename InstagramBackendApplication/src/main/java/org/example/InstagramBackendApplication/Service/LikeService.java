package org.example.InstagramBackendApplication.Service;

import org.example.InstagramBackendApplication.Model.Likes;
import org.example.InstagramBackendApplication.Repository.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {


    @Autowired
    ILikeRepo likeRepo;

    public String addLike(Likes like) {
        likeRepo.save(like);
        return "like has been added";
    }

    public String removeLike(Likes like) {
        likeRepo.delete(like);
        return "Like has been removed";
    }



    public List<Likes> getAllLikes(Integer pageNumber, Integer pageSize) {
        if (likeRepo.findAll().size() > 10) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<Likes> likesPage = likeRepo.findAll(pageable);
           List<Likes> likesList = likesPage.getContent();
           return likesList;
        } else {
            return likeRepo.findAll();
        }
    }
}


