package com.gbf.gym_buddy_finder.controller;

import com.gbf.gym_buddy_finder.model.UserProfile;
import com.gbf.gym_buddy_finder.service.UserProfileSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profile-search")
@CrossOrigin(origins = "http://localhost:3000")
public class UserProfileSearchController {
    private final UserProfileSearchService userProfileSearchService;

    public UserProfileSearchController(UserProfileSearchService userProfileSearchService) {
        this.userProfileSearchService = userProfileSearchService;
    }

    @GetMapping
    public List<UserProfile> searchProfiles(
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "gym", required = false) String gym,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder,
            @RequestParam(name = "page", defaultValue = "1") int page) {
        System.out.println("Received search parameters: age=" + age + ", gym=" + gym + ", gender=" + gender + ", sortOrder=" + sortOrder + ", page=" + page);
        return userProfileSearchService.searchProfiles(age, gym, gender, sortOrder, page);
    }
}
