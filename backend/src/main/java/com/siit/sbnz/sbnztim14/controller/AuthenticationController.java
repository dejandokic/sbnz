package com.siit.sbnz.sbnztim14.controller;

import com.siit.sbnz.sbnztim14.dto.AuthenticationRequest;
import com.siit.sbnz.sbnztim14.dto.AuthenticationResponse;
import com.siit.sbnz.sbnztim14.exceptions.AuthorizationException;
import com.siit.sbnz.sbnztim14.model.User;
import com.siit.sbnz.sbnztim14.security.TokenUtils;
import com.siit.sbnz.sbnztim14.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication REST Controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final TokenUtils tokenUtils;

    /**
     * POST /api/auth
     * Endpoint used for user authentication.
     *
     * @param authenticationRequest DTO containing login credentials
     * @return ResponseEntity containing the generated JSON Web Token
     */
    @PostMapping
    public ResponseEntity authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

        System.out.println(authenticationRequest.getUsername());
        System.out.println(authenticationRequest.getPassword());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final User user = userService.findByUsername(userDetails.getUsername());

            if(user.getAuthorities().isEmpty())
                throw new AuthorizationException("User not verified.");

            final String token = tokenUtils.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(token), HttpStatus.OK);

        } catch (BadCredentialsException ex) {
            throw new AuthorizationException("User credentials invalid.");
        }
    }
}
