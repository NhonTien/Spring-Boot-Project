package com.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(value = 101)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Qualifier("userDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000);

		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/*", "/admin", "/web/**", "/css/**", "/js/**", "/images/**", "/plugins/**",
					"/pluginss/**", "/font-awesome-4.7.0/**", "/icons-1.8.1/**", "/chart.js-3.7.1/**", "/vendor/**",
					"/moment.js-2.29.3/**", "/admin/login", "/admin/product/api/searchWeb/*",
					"/admin/brand/api/getAll", "/admin/product/api/*", "/admin/category/api/getAllCategory",
					"/productdetail/**", "/register", "/api/add", "/cart/add/*", "/cart/clear", "/cart/update",
					"/cart/del/*", "/admin/comment/api/findAll/**", "/admin/stats/hotProducts/**").permitAll()
				.antMatchers("/admin/*", "/admin/homepage", "/admin/brand", "/admin/product").access("hasRole('ROLE_ADMIN')")
				.anyRequest().authenticated()
			.and().formLogin().loginPage("/admin/login")
					.loginProcessingUrl("/loginAction")
					.defaultSuccessUrl("/admin/home")
					//.failureUrl("/admin/login?error")
					.failureUrl("/login?error")
					.usernameParameter("username").passwordParameter("password")
			//.and().logout().logoutSuccessUrl("/admin/login")
			.and().logout().logoutSuccessUrl("/")
			.and().exceptionHandling().accessDeniedPage("/admin/login");
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
}