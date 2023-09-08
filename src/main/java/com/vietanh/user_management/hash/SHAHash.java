package com.vietanh.user_management.hash;

import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;
import java.security.MessageDigest;

@Component 
public class SHAHash implements Hashing {

    @Override
    public String hashPassword(String password) {
        try {
            // Tạo một đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Băm mật khẩu bằng cách chuyển đổi thành chuỗi byte và sau đó băm
            byte[] encodedHash = digest.digest(password.getBytes());

            // Chuyển đổi chuỗi byte thành dạng hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean validatePassword(String originalPassword, String storedPassword) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Băm mật khẩu đầu vào
            byte[] inputHash = md.digest(originalPassword.getBytes());

            // Chuyển đổi thành chuỗi hex để so sánh
            StringBuilder inputHashHex = new StringBuilder();
            for (byte b : inputHash) {
                inputHashHex.append(String.format("%02x", b));
            }

            // So sánh chuỗi hex của mật khẩu đầu vào và mật khẩu đã lưu
            return inputHashHex.toString().equals(storedPassword);
        } catch (NoSuchAlgorithmException e) {
            // Xử lý ngoại lệ nếu thuật toán SHA-256 không khả dụng
            e.printStackTrace();
            return false;
        }
    }

}
