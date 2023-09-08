package com.vietanh.user_management;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.vietanh.user_management.hash.Hashing;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TestHash {
    @Autowired
    private Hashing hash;

    @Test
    public void hashPassword() {

        List<String> passwords = new ArrayList<>();

        // Thêm các chuỗi vào danh sách
        passwords.add("abcxyz");
        passwords.add("abc123");
        passwords.add("kevin@123");

        for (String password : passwords) {
            String hashed_password = hash.hashPassword(password);
            assertThat(hashed_password).isNotNull();
        }

    }

    @Test
    public void validatePassword() {

        List<String> passwords = new ArrayList<>();

        // Thêm các chuỗi vào danh sách
        passwords.add("abcxyz");
        passwords.add("abc123");
        passwords.add("kevin@123");

        for (String password : passwords) {
            String hashed_password = hash.hashPassword(password);
            assertThat(hash.validatePassword(password, hashed_password)).isTrue();
        }
        assertThat(hash.validatePassword("abc", "abc")).isFalse();
    }
}
