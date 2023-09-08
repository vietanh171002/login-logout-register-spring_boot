package com.vietanh.user_management;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.vietanh.user_management.model.User;
import com.vietanh.user_management.model.State;
import com.vietanh.user_management.repository.UserRepo;
import static org.assertj.core.api.Assertions.*;

// @SpringBootTest
public class TestUserRepo {
    @Test
    public void addUser(){
        UserRepo userRepo = new UserRepo();
        User user = userRepo.addUser("Kevin", "kevin@gmail.com", "hello123", State.PENDING);
        assertThat(user).isNotNull();
        System.out.println(user.getId());
        assertThat(user.getId()).isNotBlank();
    }

     @Test
    public void addUserWithPendingState(){
        UserRepo userRepo = new UserRepo();
        User user = userRepo.addUser("Kevin", "kevin@gmail.com", "hello123");
        assertThat(user).isNotNull();
        System.out.println(user.getState());
        assertThat(user.getId()).isNotBlank();
        assertThat(user.getState()).isEqualTo(State.PENDING);
    }

    @Test
    public void isEmailExist(){
        UserRepo userRepo = new UserRepo();
        User user = userRepo.addUser("Kevin", "kevin@gmail.com", "hello123");
        assertThat(userRepo.isEmailExist("KEvin@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("kevin@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("Abc")).isFalse();
    }

    @Test
    public void findByEmail(){
        UserRepo userRepo = new UserRepo();
        User user = userRepo.addUser("Kevin", "kevin@gmail.com", "hello123");
        assertThat(userRepo.findByEmail("kevin@gmail.com")).isNotNull();
        assertThat(userRepo.findByEmail("Kevin@gmail.com")).isNotNull();
        assertThat(userRepo.findByEmail("vin@gmail.com")).isEmpty();
        }
}