package com.naifer.wigsshop.wigsshopping.auth;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.naifer.wigsshop.wigsshopping.auth.access.AccessToken;
import com.naifer.wigsshop.wigsshopping.auth.access.AccessTokenService;
import com.naifer.wigsshop.wigsshopping.productcategories.exception.UserNotFoundException;
import com.naifer.wigsshop.wigsshopping.users.UserInfo;
import com.naifer.wigsshop.wigsshopping.users.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	public static final String SECRET = "40d3f7763153d5252dbe3899675fb08106254457769520a2d85ec3d2435a38ebf3d9b4e717ff0f0125bcc3c6a81f03437a5926cea059c0b39a0c59222f80d8f7dfe27cdc97af3c0315bb71ed732288fd4eff3dfc48ceb451450b336b1504590e2f4133086215b34ce26e033a981e31044eb034633ae11c698510666023dec374a516e91e3307bf27d2feb58f51e6af8e92b4723b02e2ec6c5e2b60695eca94441ee2a79e598f81b2c26c92e9564f2e357a08463eb363d4c1972d3a36b546510ba3b81050ffe399e7ff14f8e2bad10c24f6fd1014f720d14ad53b4e1c8aa1e1d02ad9c5368847d69364c95a8319934a1bb3536b44a979789b58521737a760fd06";
	
	//private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccessTokenService accessTokenService;
	
	public AuthToken generateToken(String userName) {
		
		Map<String, Object> claims= new HashMap<>();
		
		
		AccessToken accessToken = new AccessToken();
		
		AuthToken authToken = createToken(claims, userName);
		
		Optional<AccessToken> previousAccessToken = 
					accessTokenService.getAccessTokenByUserName(userName);
		
		if(!previousAccessToken.isEmpty()) {
			accessTokenService.deleteAccessToken(previousAccessToken.get());
		}
		
		accessToken.setRevoked(false);
		accessToken.setToken(authToken.getAccessToken());
		accessToken.setUsername(userName);
		
		accessTokenService.saveAccessToken(accessToken);
		
		return authToken;
		
	}
	
	public String removeAccessToken(String token) {
		Optional<AccessToken> accessToken = accessTokenService.getAccessToken(token);
		
		if(accessToken.isEmpty())
			throw new RuntimeException("User was not logged in.");
		
			accessTokenService.deleteAccessToken(accessToken.get());
			return "User successfuly logged out.";
		
	}
	
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
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean validateToken(String token, UserDetails userDetails ) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)) && isTokenRevoked(token);
	}
	
	private Boolean isTokenRevoked(String token) {
		Optional<AccessToken> accessToken = accessTokenService.getAccessToken(token);
		
		if(accessToken.isEmpty())
			return true;
		
		return accessToken.get().isRevoked();
	}

	private AuthToken createToken(Map<String, Object> claims, String userName) {
		
		AuthToken token = new AuthToken();
		
		token.setAccessToken(Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact());
		
	
		
		token.setTokenExpirationDate(extractExpiration(token.getAccessToken()));
		
		//UserInfo loggedUser = getLoggedUser(userName);
		
		
		return token;
		
		
	}
	
	private UserInfo getLoggedUser(String username) {
		
		Optional<UserInfo> loggedUser = userRepository.findByEmail(username);
		
		if(loggedUser.isEmpty())
			throw new UserNotFoundException("User with username "+username+ " is not found.");
		
		return loggedUser.get();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
