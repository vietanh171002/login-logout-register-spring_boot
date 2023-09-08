package com.vietanh.user_management;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.vietanh.user_management.exception.UserException;
import com.vietanh.user_management.model.User;
import static org.assertj.core.api.Assertions.*;
import com.vietanh.user_management.service.UserService;

@SpringBootTest
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        User user = userService.addUser("kevin", "kevin@gamil.com", "abc123");
        assertThat(user).isNotNull();
    }

    @Test
    public void login_when_account_is_pending () {
        User user = userService.addUser("kevin", "kevin@gmail.com","abc123");
        assertThatThrownBy(() -> { 
            userService.login("kevin@gmail.com", "abc123");
          }).isInstanceOf(UserException.class)
            .hasMessageContaining("User is not activate");
    }

    @Test
    public void login_when_account_is_not_found () {
        User user = userService.addUser("kevin", "kevin@gmail.com","abc123");
        assertThatThrownBy(() -> { 
            userService.login("kev", "abc123");
          }).isInstanceOf(UserException.class)
            .hasMessageContaining("User is not found");
    }

    @Test
    public void login_when_password_is_incorrect () {
        User user = userService.addUser("kevin", "kevin@gmail.com","abc123");
        assertThatThrownBy(() -> { 
            userService.login("kevin@gmail.com", "bc123");
          }).isInstanceOf(UserException.class)
            .hasMessageContaining("Password is incorrect");
    }

    @Test
    public void login_successfully () {
        User user = userService.addUserThenAutoActivate("kevin", "kevin@gmail.com","abc123");
        assertThat(userService.login("kevin@gmail.com", "abc123")).isNotNull();
    }

}
