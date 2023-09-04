package org.example.InstagramBackendApplication.Repository;

import org.example.InstagramBackendApplication.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends JpaRepository<Comment,Integer> {
}
