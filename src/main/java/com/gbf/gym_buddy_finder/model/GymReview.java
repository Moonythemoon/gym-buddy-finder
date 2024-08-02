package com.gbf.gym_buddy_finder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

enum Rating {
    ONE, TWO, THREE, FOUR, FIVE
}


@Setter
@Entity
public class GymReview {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="gym_id", nullable = false)
    private Gym gym;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserAccount userAccount;
// 
    @Getter
    private Rating rating;

    @Getter
    @Lob
    private String review;

    @Getter
    @CreationTimestamp
    private LocalDateTime createdAt;

}
