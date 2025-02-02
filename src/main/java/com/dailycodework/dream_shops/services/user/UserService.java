package com.dailycodework.dream_shops.services.user;

import com.dailycodework.dream_shops.exception.AlreadyExistsException;
import com.dailycodework.dream_shops.exception.ResourceNotFoundException;
import com.dailycodework.dream_shops.models.User;
import com.dailycodework.dream_shops.repositories.UserRepository;
import com.dailycodework.dream_shops.requests.CreateUserRequest;
import com.dailycodework.dream_shops.requests.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setEmail(req.getEmail());
                    user.setPassword(req.getPassword());
                    user.setFirstName(req.getFirstName());
                    user.setLastName(req.getLastName());
                    return userRepository.save(user);
                }).orElseThrow(() -> new AlreadyExistsException("Oops! " + request.getEmail() + " already exists!"));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {
        return userRepository.findById(userId).map(existingUser ->{
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    @Override
    public void deleteUSer(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () ->{
            throw new ResourceNotFoundException("User not found!");
        });
    }
}
