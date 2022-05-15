package br.ufs.esii.toh.security;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ImplementsUsuarioDetailsService usuarioDetailsService;
	@Autowired
	private ImplementsGestorDetailsService gestorDetailsService;
	@Autowired
	private ImplementsAtendenteDetailsService atendenteDetailsService;
	@Autowired
	private ImplementsAdministradorDetailsService administradorDetailsService;
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
//		http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
 		.antMatchers("/administrador").hasAnyRole("ADMIN")
 		.antMatchers("/gestor").hasAnyRole("GEST")
 		.antMatchers("/atendente").hasAnyRole("ATEN")
 		.antMatchers("/usuario").hasAnyRole("USER")
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
//		
		
//		http.csrf().disable().authorizeRequests()
	//	.antMatchers("/administrador").permitAll()
		//.anyRequest().authenticated()
		//.and().formLogin().permitAll()
		//.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin();
		
		//http.httpBasic().and().authorizeRequests().antMatchers("/api/home").hasRole("ADMIN").antMatchers("/api/product/*").hasRole("ADMIN").and().formLogin();

	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(usuarioDetailsService) 
		.passwordEncoder(passwordEncoder())
		.and().userDetailsService(atendenteDetailsService)
		.passwordEncoder(passwordEncoder())
		.and().userDetailsService(gestorDetailsService)
		.passwordEncoder(passwordEncoder())
		.and().userDetailsService(administradorDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	
	

}
