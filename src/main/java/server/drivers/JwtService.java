package server.drivers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * An implementation of IJwtService that uses an external library to provide a JWT service.
 */
@Component
public class JwtService implements IJwtService {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    private boolean useTestKey;

    public JwtService() { }

    public JwtService(boolean useTestKey) {
        this.useTestKey = useTestKey;
    }

    private String getSecretKey() {
        return useTestKey ? "test" : SECRET_KEY;
    }

    @Override
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public String createToken(String subject) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, getSecretKey()).compact();
    }

    @Override
    public boolean validateToken(String token, String subject) {
        String tokenSubject = extractSubject(token);
        return (tokenSubject.equals(subject) && !isTokenExpired(token));
    }

}