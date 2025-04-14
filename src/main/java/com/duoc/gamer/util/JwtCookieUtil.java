package com.duoc.gamer.util;

import jakarta.servlet.http.Cookie;

public class JwtCookieUtil {

    private static final String COOKIE_NAME = "JWT_TOKEN";
    private static final int COOKIE_MAX_AGE = 24 * 60 * 60;

    private JwtCookieUtil() {
    }

    public static Cookie crearCookieJWT(String jwt) {
        Cookie cookie = new Cookie(COOKIE_NAME, jwt);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(COOKIE_MAX_AGE);
        return cookie;
    }
}
