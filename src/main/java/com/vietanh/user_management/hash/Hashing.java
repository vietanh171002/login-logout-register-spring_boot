package com.vietanh.user_management.hash;

public interface Hashing {
    public String hashPassword (String password);
    public boolean validatePassword (String originalPassword, String storedPassword);
}
