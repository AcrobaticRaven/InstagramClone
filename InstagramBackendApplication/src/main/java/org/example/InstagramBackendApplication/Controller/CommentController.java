package org.example.InstagramBackendApplication.Controller;

import org.example.InstagramBackendApplication.Model.Comment;
import org.example.InstagramBackendApplication.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("Comment")
    public String addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
    @DeleteMapping("Comment")
    public String deleteComment(@RequestParam Integer Id){
        return commentService.removeComment(Id);
    }

    @GetMapping("Comments")
    public List<Comment>findAllComments(Integer pageNumber, Integer pageSize){
        return commentService.commentList(pageNumber, pageSize);
    }
}
