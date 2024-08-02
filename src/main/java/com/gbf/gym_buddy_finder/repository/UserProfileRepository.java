package com.gbf.gym_buddy_finder.repository;

import com.gbf.gym_buddy_finder.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Query(value =
            "SELECT p1.* " +
            "FROM user_profile p " +
            "JOIN gbf.user_profile p1 " +
            "ON p.city = p1.city " +
            "WHERE p.user_account_id =:id " +
            "AND p1.user_account_id <> p.user_account_id;",
            nativeQuery = true)
    List<UserProfile> findNearbyProfiles(Long id);
    
    
}
