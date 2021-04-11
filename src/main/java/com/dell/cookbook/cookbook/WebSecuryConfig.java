package com.dell.cookbook.cookbook;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
  
@Configuration
@EnableWebSecurity
public class WebSecuryConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private ImplementsUserDetailsService userDetails;
	  
	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/**").permitAll()
		.antMatchers(HttpMethod.POST, "/**").permitAll()
		
		.antMatchers(HttpMethod.POST, "/registrarUsuario").permitAll()
		.antMatchers(HttpMethod.GET, "/registrarUsuario").permitAll()
		.antMatchers(HttpMethod.GET, "/registrarUsuario").permitAll()
		.anyRequest().authenticated()
		 
		.and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/admin")
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll().and().rememberMe();
	
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/booststrap/**", "/style/**","/css/**", "/js/**", "/img/**","/uploads/**","**/favicon.ico"); 
		 
	}
	
	 
}
