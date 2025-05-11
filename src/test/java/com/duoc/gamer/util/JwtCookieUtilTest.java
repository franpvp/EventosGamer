package com.duoc.gamer.util;

import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtCookieUtilTest {

    private Cookie cookie;

    @InjectMocks
    private JwtCookieUtil jwtCookieUtil;

    @Test
    void crearCookieJWT() {

        cookie = JwtCookieUtil.crearCookieJWT("JWT");

        assertNotNull(cookie);
        assertEquals("JWT_TOKEN", cookie.getName());
        assertEquals("JWT", cookie.getValue());
        assertTrue(cookie.isHttpOnly());
        assertTrue(cookie.getSecure());
        assertEquals("/", cookie.getPath());
        assertEquals(24 * 60 * 60, cookie.getMaxAge());

    }
}