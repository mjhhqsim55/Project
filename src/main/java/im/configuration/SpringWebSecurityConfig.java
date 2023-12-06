package im.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import im.configuration.authentication.UserDaoAuthenticationProvider;
import im.dao.mapper.UserMapper;


@Configuration
@EnableWebSecurity
public class SpringWebSecurityConfig {

	@Autowired
	UserDetailsService userDetailsService ; 

	@Autowired
    DataSource datasource ; 
	
	@Autowired
	UserMapper userMapper ; 
	
	@Value("${dtrc}")
	boolean dtrc = false ; 

	@Bean
	PersistentTokenRepository jdbcTokenRepositoryImpl() {
		JdbcTokenRepositoryImpl repositoryImpl = new JdbcTokenRepositoryImpl() ;
		repositoryImpl.setCreateTableOnStartup(dtrc);
		repositoryImpl.setDataSource(datasource);
	  return repositoryImpl ;  
	}

	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
	  UserDaoAuthenticationProvider authenticationProvider 
	         = new UserDaoAuthenticationProvider() ;  
	  authenticationProvider.setHideUserNotFoundExceptions(false) ; 
	  authenticationProvider.setUserDetailsService(userDetailsService) ; 
	  authenticationProvider.setPasswordEncoder(password()) ; 
	 return authenticationProvider ; 
	}
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		UserAuthenticationSuccessHandler userAuthenticationSuccessHandler = 
				new UserAuthenticationSuccessHandler(userMapper) ; 
		userAuthenticationSuccessHandler.setDefaultTargetUrl("/success");
		userAuthenticationSuccessHandler.setAlwaysUseDefaultTargetUrl(false);
	 	
		UserAuthenticationFailureHandler authenticationFailureHandler = new 
				UserAuthenticationFailureHandler() ; 
		authenticationFailureHandler.setDefaultFailureUrl("/login?error") ; 
		authenticationFailureHandler.setUseForward(false) ; 
		// .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
		http.httpBasic(withDefaults())
				.formLogin((formconfig)->formconfig.loginPage("/login").
						loginProcessingUrl("/user/proc").
						successHandler(userAuthenticationSuccessHandler).
						failureHandler(authenticationFailureHandler).permitAll()) ;  
	 	 http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/items").hasAnyAuthority("USER")) ; 
	 	 http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/user/*").hasAnyAuthority("USER")) ; 
	 	 http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/user/manager/*").hasAnyAuthority("MANAGER")) ; 
	 	 http.authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll()) ; 
		 http.exceptionHandling((customizer)->customizer.accessDeniedPage("/unauth")) ;

		 http.logout(out->out.logoutUrl("/out").logoutSuccessUrl("/")) ; 
	    // http.authorizeHttpRequests(cust->cust.requestMatchers("/date").hasAnyAuthority("USER")) ; 
		 http.rememberMe(remember->remember.tokenRepository(jdbcTokenRepositoryImpl()).
		 		 tokenValiditySeconds(120).userDetailsService(userDetailsService).
		 		 authenticationSuccessHandler(userAuthenticationSuccessHandler)) ; 
		 http.csrf(csrf->csrf.ignoringRequestMatchers("/register/prod")); 
         //  http.headers(cust->cust.frameOptions(c->c.sameOrigin())) ; 

		 http.authenticationProvider(daoAuthenticationProvider() ) ; 
	   return http.build(); 
	}

	@Bean
    PasswordEncoder password() {
    	return new BCryptPasswordEncoder() ; 
    }

}

