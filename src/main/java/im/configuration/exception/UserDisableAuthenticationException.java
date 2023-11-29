package im.configuration.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDisableAuthenticationException extends UsernameNotFoundException {

	private static final long serialVersionUID = 1L;

	
	public UserDisableAuthenticationException(String ex) {
		super(ex) ; 
	}
	
}
