package com.Lucas_Santos732.finPlus.service;//package com.Lucas_Santos732.finPlus.service;

//import com.Lucas_Santos732.finPlus.controller.dto.CreateUserDto;
//import com.Lucas_Santos732.finPlus.controller.dto.UpdateUserDto;
//import com.Lucas_Santos732.finPlus.entity.User;
//import com.Lucas_Santos732.finPlus.repository.UserRepository;
//import org.springframework.stereotype.Service;

//import java.time.Instant;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
/*
@Service
public class UserService {

    private static UserRepository userRepository = null;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {
        User user = new User(
                null,
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null
        );

        User savedUser = userRepository.save(user);
        return savedUser.getUserId();
    }

    public static Optional<User> getUserById(String userId) {

        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId,
                               UpdateUserDto updateUserDto) {
        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDto.username() != null) {
                user.setUsername(updateUserDto.username());
            }
            if (updateUserDto.password() != null) {
                user.setPassword(updateUserDto.password());
            }
            userRepository.save(user);

        }
    }


    public void deleteById(String userId) {
        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }
}
*/