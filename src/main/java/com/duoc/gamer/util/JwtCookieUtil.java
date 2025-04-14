package com.duoc.gamer.util;

import jakarta.servlet.http.Cookie;

public class JwtCookieUtil {

    private static final String COOKIE_NAME = "JWT_TOKEN";
    private static final int COOKIE_MAX_AGE = 24 * 60 * 60; // 1 día

    private JwtCookieUtil() {
    }

    public static Cookie crearCookieJWT(String jwt) {
        Cookie cookie = new Cookie(COOKIE_NAME, jwt);
        cookie.setSecure(true);           // solo se envía por HTTPS
        cookie.setHttpOnly(true);         // no accesible desde JS
        cookie.setPath("/");              // accesible en toda la app
        cookie.setMaxAge(COOKIE_MAX_AGE); // duración
        return cookie;
    }
}
