package com.gbf.gym_buddy_finder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;


enum Gender {
    MALE, FEMALE, OTHER;
}

@Setter
@Entity
public class UserProfile {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private UserAccount userAccount;

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    private String profilePictureUrl;

    @Getter
    @Lob
    private String bio;

    @Getter
    private LocalDate dob;

    @Transient
    private Integer age;

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    @Getter
    private Gender gender;

    @Getter
    private String address;

    @Getter
    private String city;

    @Getter
    private String province;

    @Getter
    private String country;

    @Getter
    private String postCode;

    @Getter
    private boolean isActive;

    @Getter
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Getter
    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
