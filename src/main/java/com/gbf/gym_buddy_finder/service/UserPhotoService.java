package com.gbf.gym_buddy_finder.service;

import com.gbf.gym_buddy_finder.model.UserPhoto;
import com.gbf.gym_buddy_finder.repository.UserPhotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPhotoService {
    private final UserPhotoRepository userPhotoRepository;

    public UserPhotoService(UserPhotoRepository userPhotoRepository) {
        this.userPhotoRepository = userPhotoRepository;
    }

    public List<UserPhoto> getPhotosByUserId(Long userId) {
        return userPhotoRepository.findByUserAccountId(userId);
    }
}
