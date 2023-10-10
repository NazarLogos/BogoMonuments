package bogomonuments.com.service;

import bogomonuments.com.dto.UserDto;
import bogomonuments.com.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
