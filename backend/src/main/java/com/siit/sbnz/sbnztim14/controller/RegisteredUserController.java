package com.siit.sbnz.sbnztim14.controller;

import com.siit.sbnz.sbnztim14.dto.RegisteredUserDTO;
import com.siit.sbnz.sbnztim14.dto.SuccessResponse;
import com.siit.sbnz.sbnztim14.model.RegisteredUser;
import com.siit.sbnz.sbnztim14.model.User;
import com.siit.sbnz.sbnztim14.service.RegisteredUserService;
import com.siit.sbnz.sbnztim14.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Registered User management REST controller.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class RegisteredUserController {

    private final RegisteredUserService registeredUserService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity handleCreate(@RequestBody RegisteredUserDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        final RegisteredUser registeredUser = registeredUserService.create(dto.convertToEntity());
        return new ResponseEntity<>(registeredUser.getId(), HttpStatus.CREATED);
    }

    /**
     * GET /api/users/verify/{verificationCode}
     * Endpoint used for {@link RegisteredUser} account verification.
     *
     * @param verificationCode Verification code string
     * @return {@link ResponseEntity} containing HttpStatus and message of the operation result
     */
    @GetMapping(value="verify/{verificationCode}")
    public ResponseEntity<SuccessResponse> handleVerify(@PathVariable("verificationCode") String verificationCode) {
        return new ResponseEntity(new SuccessResponse("User verified successfully."), HttpStatus.OK);
    }

    /**
     * GET /api/users/me
     * Endpoint used to get the currently active {@link RegisteredUser}
     *
     * @return {@link ResponseEntity} containing HttpStatus and current user data
     */
    @GetMapping(value = "me")
    @PreAuthorize("hasAuthority('REGISTERED_USER')")
    public ResponseEntity getCurrentUser() {
        final User user = this.userService.findCurrentUser();
        final RegisteredUser registeredUser = this.userService.findRegisteredUserByUsername(user.getUsername());
        return new ResponseEntity<>(new RegisteredUserDTO(registeredUser), HttpStatus.OK);
    }

}
