package com.codeoftheweb.salvo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		String[] whiteList = { "/web/games.html", "/scripts/**", "/rest/**", "/api/games", "/api/login", "/styles/**" };

		http.formLogin(login -> login
				.loginProcessingUrl("/login")
				.successHandler((req, res, auth) -> clearAuthenticationAttributes(req))
				.failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED)));

		http.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()));

		http.authorizeRequests(requests -> requests
				.antMatchers(whiteList).permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/users/**").hasAnyAuthority("ADMIN", "USER")
				.antMatchers("/**").permitAll());

		http.csrf(AbstractHttpConfigurer::disable);

		http.exceptionHandling(handling -> handling
				.authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED)));
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}
}
