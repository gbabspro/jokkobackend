package com.jokkoapps.jokkoapps.controller;

import com.jokkoapps.jokkoapps.repository.ManagerRepository;
import com.jokkoapps.jokkoapps.exception.ResourceNotFoundException;
import com.jokkoapps.jokkoapps.model.Manager;
import com.jokkoapps.jokkoapps.payload.*;
import com.jokkoapps.jokkoapps.repository.ManagerRepository;
import com.jokkoapps.jokkoapps.security.ManagerPrincipal;
import com.jokkoapps.jokkoapps.security.CurrentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    ManagerRepository managerRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    public UserSummary getCurrentUser(@CurrentManager ManagerPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFirstname(), currentUser.getLastname());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !managerRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !managerRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        Manager manager = managerRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        UserProfile userProfile = new UserProfile(manager.getId(), manager.getUsername(), manager.getFirstname(), manager.getLastname(), manager.getEmail());

        return userProfile;
    }


}