package com.siit.sbnz.sbnztim14.service;

import com.siit.sbnz.sbnztim14.model.RegisteredUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Registered User service layer.
 */
@Service
@RequiredArgsConstructor
public class RegisteredUserService {

    private final UserService userService;
    /**
     * Creates a new Registered User and sends verification email
     *
     * @param registeredUser {@link RegisteredUser} instance to be created
     * @return created {@link RegisteredUser} instance
     */
    public RegisteredUser create(RegisteredUser registeredUser) {
        this.userService.checkIfUsernameTaken(registeredUser.getUsername());
        this.userService.checkIfEmailTaken(registeredUser.getEmail());
        return this.userService.save(registeredUser);
    }

}
