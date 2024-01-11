package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.model.Player;
import com.codeoftheweb.salvo.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
	PlayerRepository playerRepository;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider());
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new DaoAuthenticationProvider() {
			{
				setUserDetailsService(username -> {
					Player player = playerRepository.findByUserName(username);
					if (player != null) {
						return new User(player.getUserName(), player.getPassword(), AuthorityUtils.createAuthorityList("USER"));
					} else {
						throw new UsernameNotFoundException("Unknown user " + username);
					}
				});
			}
		};
	}
}
