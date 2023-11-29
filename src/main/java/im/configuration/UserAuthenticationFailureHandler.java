package im.configuration;

import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
 
	private String defaultFailureUrl;

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl ; 
	}
	
	public void onAuthenticationFailure(HttpServletRequest request, 
			           HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		 
		HttpSession session = request.getSession(false);
		if (session != null) {
			request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception.getMessage());
		}
		 response.sendRedirect(defaultFailureUrl); 
		  
	}
	
}




