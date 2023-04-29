package com.gl.studenteventreg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication().withUser(users.username("kumar").password("123").roles("ADMIN"))
				.withUser(users.username("amit").password("123").roles("USER"));

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").hasAnyRole("USER", "ADMIN").antMatchers("/home")
				.hasAnyRole("USER", "ADMIN").antMatchers("/view/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/insert/**").hasRole("ADMIN").antMatchers("/delete/**").hasRole("ADMIN")
				.antMatchers("/print/**").hasRole("ADMIN").antMatchers("/new/**").hasRole("ADMIN").and().formLogin()
				.loginPage("/showMyLoginPage").loginProcessingUrl("/authenticateTheUser").permitAll().and().logout()
				.permitAll().and().exceptionHandling().accessDeniedPage("/access-denied");
	}

}
