package com.template.template.auth.service;

import com.template.template.auth.mapper.AuthMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Autowired
    AuthMapper authMapper;

    public static final String SECRET = "b2R4kz8X3Nd65cLjVcQwMPL2R4TzYkN74PsQ5Mv12345";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Date time() {
        return new Date(System.currentTimeMillis() + 2000 * 60 * 60); // 2h
    }

//public Date time() {
//    return new Date(System.currentTimeMillis() + 1000 * 60); // 1p
//}

    public String createToken(String username) {
        Date time = time();
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(time)
                .signWith(getSignKey(), Jwts.SIG.HS256)
                .compact();
    }

    public Date timeRefresh() {
        return new Date(System.currentTimeMillis() + 1000 * 60); // Refresh token hết hạn sau 10h
    }

    public String createRefreshToken(String username) {
        Date time = timeRefresh();
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(time)
                .signWith(getSignKey(), Jwts.SIG.HS256)
                .compact();
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public List<HashMap<String, String>> getUserAndRole(String input) {
        return this.authMapper.getUserAndRole(input);
    }

    // Phương thức kiểm tra refresh token có hết hạn không
    public Boolean isRefreshTokenExpired(String refreshToken) {
        return extractExpiration(refreshToken).before(new Date());
    }

    // Phương thức làm mới access token bằng refresh token
    public String refreshAccessToken(String refreshToken) {
        if (isRefreshTokenExpired(refreshToken)) {
            throw new ExpiredJwtException(null, null, "Refresh token expired");
        }

        String username = extractUsername(refreshToken);  // Lấy tên người dùng từ refresh token
        return createToken(username);  // Tạo access token mới
    }
}
