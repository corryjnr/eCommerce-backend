package com.dailycodework.dream_shops.services.user;

import com.dailycodework.dream_shops.models.User;
import com.dailycodework.dream_shops.requests.CreateUserRequest;
import com.dailycodework.dream_shops.requests.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUSer(Long userId);
}
