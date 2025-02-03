package com.dailycodework.dreamshops.services.user;

import com.dailycodework.dreamshops.dto.UserDto;
import com.dailycodework.dreamshops.models.User;
import com.dailycodework.dreamshops.requests.CreateUserRequest;
import com.dailycodework.dreamshops.requests.UserUpdateRequest;

public interface IUserService {
    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUSer(Long userId);

    UserDto convertToDto(User user);
}
