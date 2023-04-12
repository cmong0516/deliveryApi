package hello.mong.utils;

import hello.mong.domain.Role;
import hello.mong.domain.entity.Authority;
import hello.mong.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private Key key;

    private final long exp = 1000L * 60 * 60;

    private final CustomUserDetailsService customUserDetailsService;

    @PostConstruct
    protected void init() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String username, List<Authority> roles) {
        log.info("-> JwtProvider.createToken");
        log.info("JwtProvider.createToken() username = {}",username);
        log.info("JwtProvider.createToken() role = {}",roles.toString());

        // username , role 을 받아 토큰 생성
        Claims claims = Jwts.claims().setSubject(username);
        // jjwt library 를 사용하여 claims 를 생성하고 setSubject(username)
        claims.put("roles",roles);
        // 생성한 클래임에 키 "role" 값 role 설정.
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                // 생선한 클래임
                .setIssuedAt(now)
                // 토큰 생성시간.
                .setExpiration(new Date(now.getTime() + exp))
                // 토큰 만료시간 설정
                .signWith(key, SignatureAlgorithm.HS256)
                // 비밀키 key 로 확인후 HS256 알고리즘을 이용하여 토큰 생성.
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(this.getMember(token));

        log.info("userDetails.getUsername() = {}",userDetails.getUsername());
        log.info("userDetails.getPassword() = {}",userDetails.getPassword());
        log.info("userDetails.getAuthorities().toString() = {}",userDetails.getAuthorities().toString());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getMember(String token) {
        log.info("-> JwtProvider.getMember()");
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        log.info("-> JwtProvider.resolveToken()");

        return request.getHeader("Authorization");
    }

    public boolean validateToken(String token) {
        log.info("JwtProvider.validateToken() token = {}",token);
        log.info(token.substring(0, "BEARER ".length()));
        log.info("token = token.split(\" \")[1].trim() = {}",token.split(" ")[1].trim());

        try {
            if (!token.substring(0, "BEARER ".length()).equalsIgnoreCase("BEARER ")) {
                return true;
            } else {
                token = token.split(" ")[1].trim();
            }

            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
