package com.gbf.gym_buddy_finder.controller;

import com.gbf.gym_buddy_finder.model.UserPhoto;
import com.gbf.gym_buddy_finder.service.UserPhotoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/photo")
@CrossOrigin(origins = "http://localhost:3000")
public class UserPhotoController {
    private final UserPhotoService userPhotoService;

    public UserPhotoController(UserPhotoService userPhotoService) {
        this.userPhotoService = userPhotoService;
    }

    @GetMapping("{userId}")
    public List<UserPhoto> getPhotosByUserId(@PathVariable("userId") Long userId) {
        return userPhotoService.getPhotosByUserId(userId);
    }
}
