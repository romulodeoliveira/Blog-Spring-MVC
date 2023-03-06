package com.spring.blog.configurations;

import java.security.SecureRandom;
import java.util.Base64;

public class Constantes {
    public static final String myKey;

    static {
        SecureRandom random = new SecureRandom();
        byte[] chave = new byte[16]; // 16 bytes = 128 bits
        random.nextBytes(chave);
        myKey = Base64.getEncoder().encodeToString(chave);
    }
}
