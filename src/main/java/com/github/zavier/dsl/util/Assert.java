package com.github.zavier.dsl.util;

public class Assert {

    public static void assertNotNull(Object obj, String message) {
        assertTrue(obj != null, message);
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }
}
