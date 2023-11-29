package im.configuration.exception;

import org.springframework.security.core.AuthenticationException;

public class PasswordNotMatchAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	
	public PasswordNotMatchAuthenticationException(String ex) {
		super(ex) ; 
	}
	
}
