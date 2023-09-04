package org.example.InstagramBackendApplication.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String commentText;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate commentAddedDate;

    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    Post commentedPost;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    User commentedUser;
}
