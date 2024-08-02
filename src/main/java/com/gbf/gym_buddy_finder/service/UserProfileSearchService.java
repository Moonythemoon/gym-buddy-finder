package com.gbf.gym_buddy_finder.service;

import com.gbf.gym_buddy_finder.model.UserProfile;
import com.gbf.gym_buddy_finder.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileSearchService {
    private final UserProfileRepository userProfileRepository;

    public UserProfileSearchService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public List<UserProfile> searchProfiles(Integer age, String gym, String gender, String sortOrder, int page) {
        // Implement the search logic here
        // For simplicity, we return all profiles
        return userProfileRepository.findAll();
    }
}
