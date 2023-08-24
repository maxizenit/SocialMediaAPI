package ru.maxizenit.socialmediaapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Duration;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/** Вспомогательный класс для операций над JWT-токенами. */
@Component
public class JwtTokenUtils {

  /** Секретный ключ. */
  @Value("${jwt.secret}")
  private String secret;

  /** Время жизни токена. */
  @Value("${jwt.lifetime}")
  private Duration lifetime;

  /**
   * Генерирует токен для пользователя.
   *
   * @param userDetails информация о пользователе
   * @return токен
   */
  public String generateToken(UserDetails userDetails) {
    Date issuedDate = new Date();
    Date expiredDate = new Date(issuedDate.getTime() + lifetime.toMillis());
    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(issuedDate)
        .setExpiration(expiredDate)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }

  /**
   * Возвращает имя пользователя из токена
   *
   * @param token токен
   * @return имя пользователя из токена
   */
  public String getUsernameFromToken(String token) {
    return getAllClaimsFromToken(token).getSubject();
  }

  /**
   * Возвращает Claims из токена.
   *
   * @param token токен
   * @return Claims из токена
   */
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }
}
