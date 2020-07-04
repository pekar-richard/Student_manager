package com.example.demo.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource securityDataSource;
 
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    	// use jdbc authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);
    }
	
    @Override
    public void configure(WebSecurity web) throws Exception {
         web.ignoring()
         .antMatchers("/css/**")
         .antMatchers("/js/**");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	/*http.authorizeRequests()	
    	.antMatchers("/**").permitAll();*/
		
		http.csrf().disable().authorizeRequests()	
		.antMatchers("/api/login").permitAll()
		.antMatchers("/api/getuser").permitAll()
		.antMatchers("/**").hasRole("USER")
        .antMatchers(HttpMethod.POST, "/**").hasRole("USER")
        .antMatchers(HttpMethod.PUT, "/**").hasRole("USER")
        .antMatchers(HttpMethod.PATCH, "/**").hasRole("USER")
        .antMatchers(HttpMethod.DELETE, "/**").hasRole("USER")
        
	.and()	
	.formLogin()
		.loginPage("/showMyLoginPage")
		.loginProcessingUrl("/authenticateTheUser")
		.defaultSuccessUrl("http://localhost:3000/dashboard")
		.permitAll()
	.and()	
	.logout().permitAll()
	.and()
	.exceptionHandling().accessDeniedPage("/access-denied");
    }
}
