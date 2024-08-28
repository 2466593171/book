package org.example.book;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

@SpringBootTest
class BookApplicationTests {

    @Test
    void contextLoads() {
        byte[] secretKeyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        String secretKey = Base64.getEncoder().encodeToString(secretKeyBytes);
        System.out.println(secretKey);
    }

    public static void main(String[] args) {
        String s = "abc";
        if(s.equals("bc")) {
            throw new NumberFormatException();
        } else {
            System.out.println(s);
        }
        //function();
    }

}
