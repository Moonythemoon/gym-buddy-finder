package com.gbf.gym_buddy_finder.controller;

import com.gbf.gym_buddy_finder.model.UserProfile;
import com.gbf.gym_buddy_finder.service.UserProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profile")
@CrossOrigin(origins = "http://localhost:3000")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("{id}")
    public UserProfile getProfileById(@PathVariable("id") Long id) {
        return userProfileService.getProfileById(id);
    }

    @GetMapping("nearby")
    public List<UserProfile> getNearbyProfiles(@RequestParam(name = "id") Long id) {
        return userProfileService.getNearbyProfiles(id);
    }
    


}
