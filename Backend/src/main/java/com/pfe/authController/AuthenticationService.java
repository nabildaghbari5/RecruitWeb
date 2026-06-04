package com.pfe.authController;

import com.pfe.model.Role;
import com.pfe.model.Token;
import com.pfe.model.User;
import com.pfe.repository.TokenRepository;
import com.pfe.repository.UserRepository;
import com.pfe.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.management.relation.RoleNotFoundException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;


    public User register(RegistrationRequest request) throws RoleNotFoundException {
        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RoleNotFoundException("Role invalide : " + request.getRole());
        }
        // Creation de l'utilisateur
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .role(role)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()) )
                .telephone(request.getTelephone())
                .accountLocked(false)
                .enabled(true)
                .status(request.getStatus())
                .build();
        userRepository.save(user);
        return user;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        try {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var user = (User) auth.getPrincipal();

            var claims = new HashMap<String, Object>();
            claims.put("fullName", user.getFirstname() + " " + user.getLastname());

            var jwtToken = jwtService.generateToken(claims, user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .id(user.getId())
                    .email(user.getEmail())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .roles(user.getRole())
                    .build();

        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw e;
        }
        }


}
