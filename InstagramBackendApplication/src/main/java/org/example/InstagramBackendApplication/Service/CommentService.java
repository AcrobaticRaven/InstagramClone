package org.example.InstagramBackendApplication.Service;

import org.example.InstagramBackendApplication.Model.Comment;
import org.example.InstagramBackendApplication.Repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    ICommentRepo commentRepo;

    public String addComment(Comment comment) {
        comment.setCommentAddedDate(LocalDate.now());
        commentRepo.save(comment);
        return "Comment has been added successfully";
    }

    public String removeComment(Integer Id) {
        Optional<Comment> commentsOptional = commentRepo.findById(Id);
        if (commentsOptional.isPresent()) {
            Comment comment = commentsOptional.get();
                commentRepo.delete(comment);
                return "Comment has been deleted successfully";
        } else {
            return "Comment not found of Id : " + Id;
        }
    }

    public List<Comment> commentList(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(Sort.Order.desc("commentId")));
        Page<Comment> commentPage = commentRepo.findAll(pageable);
        List<Comment> commentList = commentPage.getContent();

        return commentList;
    }
}