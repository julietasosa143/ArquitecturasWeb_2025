package com.example.demo.util;

public class JwtUtil {

    private static final ThreadLocal<String> tokenHolder = new ThreadLocal<>();

    public static void setTokenActual(String token) {
        tokenHolder.set(token);
    }

    public static String getTokenActual() {
        return tokenHolder.get();
    }

    public static void clear() {
        tokenHolder.remove();
    }
}
