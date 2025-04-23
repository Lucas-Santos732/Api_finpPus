package com.Lucas_Santos732.finPlus.controller;

import com.Lucas_Santos732.finPlus.controller.dto.LoginAccessUserDto;
import com.Lucas_Santos732.finPlus.controller.dto.LoginUserDto;
import com.Lucas_Santos732.finPlus.entity.Role;
import com.Lucas_Santos732.finPlus.entity.User;
import com.Lucas_Santos732.finPlus.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TokenController {
    private final JwtEncoder jwtEncoder;

    private final UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder jwtEncoder,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = passwordEncoder;

    }


    @PostMapping("/login")
    public ResponseEntity<LoginAccessUserDto> login(@RequestBody LoginUserDto loginUserDto) {

       var user = userRepository.findByUsername(loginUserDto.username());

       if(user.isEmpty() || !user.get().isLoginCorrect(loginUserDto, bCryptPasswordEncoder)) {
           throw new BadCredentialsException("user or password is invalid!");
       }

       var now = Instant.now();
       var expiresIn = 10000L;

       var scopes = user.get().getRoles().stream().map(Role::getName).collect(Collectors.joining(""));

       var claims = JwtClaimsSet.builder()
               .issuer("mybackend")
               .subject(user.get().getUserId().toString())
               .issuedAt(now)
               .expiresAt(now.plusSeconds(expiresIn))
               .claim("scope", scopes)
               .build();

       var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

       return ResponseEntity.ok(new LoginAccessUserDto(jwtValue, expiresIn));

    }
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<List<User>> listUsers() {
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

}
