package org.example.book.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
@Configuration
public class TokenUtil {

        private static final String SECRET_KEY = "Bf0AKGqXIwWklAptiWnEU21aAJ/t03L3U0YINCBL8hlmS1OPMgTm5H8or/CRIQx0i3tPqsOym1n9vuPdCA0YVw=="; // 设置密钥

        public String generateToken(String username,Long id) {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + 3600000); // 设置token过期时间为1小时

            return Jwts.builder()
                    .setSubject(username)
                    .setId(String.valueOf(id))
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                    .compact();
        }

        public String getUsernameFromToken(String token) {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        }

    public String getIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getId();
    }

        public boolean validateToken(String token) {
            try {
                Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

}
