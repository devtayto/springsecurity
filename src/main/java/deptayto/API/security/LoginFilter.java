package deptayto.API.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import deptayto.API.dto.UsersDto;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	public LoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.printf("JWTLoginFilter.attemptAuthentication: username/password= %s,%s", username, password);
		System.out.println();
		// check method post when authen with json
		if ("POST".equals(request.getMethod())) {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();
			try {
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line).append('\n');
				}
			} finally {
				reader.close();
			}
			// converter from json to object
			System.out.println(sb.toString());
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			UsersDto user = new UsersDto();
			user = objectMapper.readValue(sb.toString(), UsersDto.class);
			// set back username and password
			username = user.getUserName();
			password = user.getPassword();

		}
		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("JWTLoginFilter.successfulAuthentication:");
		// Write Authorization to Headers of Response.
		TokenAuthenticationService.addAuthentication(response, authResult.getName());
		String authorizationString = response.getHeader("Authorization").replace("Bearer ", "");
		System.out.println("Authorization String=" + authorizationString);
		// return json to body request
		response.getWriter().print(String.format("{\"token\":\"%s\"}", authorizationString));

	}

}
