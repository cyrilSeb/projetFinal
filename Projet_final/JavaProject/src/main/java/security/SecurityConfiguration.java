package security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import service.CustomUserDetailsService;



@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired // pour etre injecté d'office
	DataSource dataSource;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.headers().frameOptions().disable();

		// http.authorizeRequests().anyRequest().permitAll(); //active couche
		// spring security. Ici on autorise acces a toutes nos pages,
		http.authorizeRequests().antMatchers("/adherent/edit*").authenticated().and().formLogin().loginPage("/login").failureUrl("/login?error=erreur").and().logout().logoutSuccessUrl("/adherent/list"); // antmatcher
																									// est
																									// la
																									// pour
																									// definir
																									// des
																									// masques
		http.authorizeRequests().anyRequest().permitAll();
	}

	// @Autowired //methode pour authenti avec login en memoire
	// public void configAuthentification (AuthenticationManagerBuilder auth)
	// throws Exception{
	// auth.inMemoryAuthentication().withUser("toto").password("{noop}tutu").roles("ADMIN");
	// //noop permet de dire que mdp pas encodé et le prend telle quel
	// }

//	@Autowired			//a faire si l'on a peu de chose a faire plutot que d'utiliser le service comme en dessous
//	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.jdbcAuthentication().dataSource(dataSource)
//				.usersByUsernameQuery("Select username, password, enable from users where username=?")
//				.authoritiesByUsernameQuery("Select username, role from user_roles where username=?");
//	}
	
	@Autowired	
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
			auth.userDetailsService(customUserDetailsService).passwordEncoder(getPasswordEncoder());
		}
	
	@Bean(name="passwordEncoder")
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
