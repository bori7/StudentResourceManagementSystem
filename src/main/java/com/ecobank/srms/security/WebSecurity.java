package com.ecobank.srms.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.Collections;

import static com.ecobank.srms.security.SecurityConstants.GET_AUTH_TOKEN;
import static com.ecobank.srms.security.SecurityConstants.LOGIN;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {


//	@Value(value = "${compliance.crossorigin.uris}")
//	private String crossoriginURIs;


	private JwtAuthenticationEntryPoint unauthorizedHandler;

	public WebSecurity(JwtAuthenticationEntryPoint unauthorizedHandler) {
		this.unauthorizedHandler = unauthorizedHandler;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
				.and()
				.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.and()
				.authorizeRequests().antMatchers(GET_AUTH_TOKEN,LOGIN,"/api/v1/user/register", "/api/v1/admin/register", "/api/v1/admin/login", "/api/v1/admin/reset_password"
															,"/api/v1/teacher/register","/api/v1/teacher/login"
						                                    , "/api/v1/student/register", "/api/v1/student/login","/api/v1/student/reset_password"
						                                     , "/api/v1/tokenPlain","/webjars/**", "/swagger-ui*/**", "/v3/api-docs/**")
				.permitAll().anyRequest().authenticated()
				.and()
				.addFilter(new JWTAuthorizationFilter(authenticationManager())).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.headers()
				.xssProtection()
				.and()
				//.contentSecurityPolicy("script-src 'self' script-src 'strict-dynamic' 'nonce-rAnd0m123' 'unsafe-inline' http: https:; object-src 'none'; base-uri 'none'; require-trusted-types-for 'script';")

		;
				// this disables session creation on Spring Security


	}

	//	BILLERS, ACCOUNTS
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}


//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//		return source;
//	} 
//	public static final String LOGIN_WITHOUT_TOKEN = "/api/v1/loginWithoutToken";

	/*@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();
		//configuration.setAllowedOrigins(Arrays.asList(crossoriginURIs.split(",")));
		configuration.setAllowedOrigins(Collections.singletonList("*"));
		configuration.setAllowedMethods(Arrays.asList( "GET", "POST","OPTIONS"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(

				Arrays.asList(
						"Access-Control-Allow-Headers",
						"Access-Control-Allow-Origin",
						"Access-Control-Request-Method",
						"Access-Control-Request-Headers",
						"Origin", "Cache-Control",
						"Content-Type","Accept",
						"Authorization",
						"x-client-id",
						"x-client-secret",
						"x-source-code"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}*/


//	@Bean
//	public CorsFilter corsFilter(){
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
//		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD"));
//		corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
//		corsConfiguration.setExposedHeaders(Collections.singletonList("x-auth-token"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", corsConfiguration);
//
//		return new CorsFilter((CorsConfigurationSource) source);
//	}
//
//
//
}