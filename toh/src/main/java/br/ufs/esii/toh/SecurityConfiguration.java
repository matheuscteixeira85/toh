package br.ufs.esii.toh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		
//		http.csrf().disable().authorizeRequests()
	//	.antMatchers(HttpMethod.GET, "/administrador").permitAll()
		//.anyRequest().authenticated()
		//.and().formLogin().permitAll()
		//.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin();
		//http.httpBasic().and().authorizeRequests().antMatchers("/api/home").hasRole("ADMIN").antMatchers("/api/product/*").hasRole("ADMIN").and().formLogin();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).authorities("USER");
	}
	
	

}
