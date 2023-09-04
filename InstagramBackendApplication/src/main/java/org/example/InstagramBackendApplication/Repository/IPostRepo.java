package org.example.InstagramBackendApplication.Repository;

import org.example.InstagramBackendApplication.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<Post,Integer> {

}
