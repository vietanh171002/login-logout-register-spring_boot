package com.vietanh.user_management.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.vietanh.user_management.exception.UserException;
import com.vietanh.user_management.hash.Hashing;
import com.vietanh.user_management.model.State;
import com.vietanh.user_management.model.User;
import com.vietanh.user_management.repository.UserRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor


public class UserServiceInMemory implements UserService {

    private UserRepo userRepo;
    private Hashing hashing;

    @Override
    public User login(String email, String password) {
        Optional<User> o_user = userRepo.findByEmail(email);
        if (!o_user.isPresent()) {
            throw new UserException("User is not found");
        }
        User user = o_user.get();
        if (hashing.validatePassword(password, user.getHashed_password())) {
            if (user.getState() != State.ACTIVE) {
                throw new UserException("User is not activated");
            }
            else{
                return user;
            }
        }
        else {
            throw new UserException("Password is incorrect");
        }
    }

    @Override
    public Boolean logout(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }

    @Override
    public User addUser(String fullname, String email, String password) {
        return userRepo.addUser(fullname, email, hashing.hashPassword(password));
    }

    @Override
    public User addUserThenAutoActivate(String fullname, String email, String password) {
        return userRepo.addUser(fullname, email, hashing.hashPassword(password), State.ACTIVE);
    }

    @Override
    public Boolean activateUser(String activation_code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'activateUser'");
    }

    @Override
    public Boolean updatePassword(String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
    }

    @Override
    public Boolean updateEmail(String email, String newEmail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEmail'");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public User fingById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fingById'");
    }

}
