package im.configuration.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import im.configuration.exception.PasswordNotMatchAuthenticationException;

public class UserDaoAuthenticationProvider extends DaoAuthenticationProvider {

	 
	
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			this.logger.debug("Failed to authenticate since no credentials provided");
			throw new PasswordNotMatchAuthenticationException("password does not match") ; 
		}
		String presentedPassword = authentication.getCredentials().toString();
		if (!getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
			this.logger.debug("Failed to authenticate since password does not match stored value");
			throw new PasswordNotMatchAuthenticationException("password does not match") ; 
		}
	}
	
}
