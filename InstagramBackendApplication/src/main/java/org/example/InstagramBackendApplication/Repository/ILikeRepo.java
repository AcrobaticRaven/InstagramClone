package org.example.InstagramBackendApplication.Repository;

import org.example.InstagramBackendApplication.Model.Likes;
import org.example.InstagramBackendApplication.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeRepo extends JpaRepository<Likes,Integer> {
    List<Likes> findByLikedPost(Post validPost);

}
