package com.gbf.gym_buddy_finder.repository;

import com.gbf.gym_buddy_finder.model.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPhotoRepository extends JpaRepository<UserPhoto, Long> {
    List<UserPhoto> findByUserAccountId(Long userId);
}
