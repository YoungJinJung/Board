package com.example.board.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {
    @Test
    public void encodePassword() {
        String encPassword = new BCryptPasswordEncoder().encode("1234");
        Assert.assertNotEquals(encPassword, "1234");
    }
}
