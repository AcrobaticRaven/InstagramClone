package org.example.InstagramBackendApplication.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer likeId;
    private boolean isLikeAlready;


    @OneToOne
    @JoinColumn(name =  "fk_post_id")
    private Post likedPost;

    @ManyToOne
    @JoinColumn(name = "fk_liker_id")
    private User likedUser;
}
