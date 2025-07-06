package com.becoder.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

	private static final String KEY_FILE_PATH = "jwt-secret.key";
	private SecretKey secretKey;

	@PostConstruct
	public void init() {
		File keyFile = new File(KEY_FILE_PATH);
		if (keyFile.exists()) {
			loadKeyFromFile();
		} else {
			generateAndSaveKeyToFile();
		}
	}

	private void generateAndSaveKeyToFile() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			keyGen.init(256);
			this.secretKey = keyGen.generateKey();

			String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

			try (FileWriter writer = new FileWriter(KEY_FILE_PATH)) {
				writer.write(encodedKey);
			}

		} catch (Exception e) {
			throw new RuntimeException("Failed to generate and save secret key", e);
		}
	}

	private void loadKeyFromFile() {
		try {
			String encodedKey;
			try (BufferedReader reader = new BufferedReader(new FileReader(KEY_FILE_PATH))) {
				encodedKey = reader.readLine();
			}

			byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
			this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");

		} catch (Exception e) {
			throw new RuntimeException("Failed to load secret key from file", e);
		}
	}

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", username);

		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5)) // 5 hours
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}

	private SecretKey decryptKey(String encodedKey) {
		byte[] keyBytes = Base64.getDecoder().decode(encodedKey);
		return new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
	}

	public String extractUserName(String token) {
		return extractAllClaims(token).getSubject();
	}

	public boolean validateToken(String token, org.springframework.security.core.userdetails.UserDetails userDetails) {
		final String username = extractUserName(token);
		return (username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		Date expiration = extractAllClaims(token).getExpiration();
		return expiration.before(new Date());
	}

}
