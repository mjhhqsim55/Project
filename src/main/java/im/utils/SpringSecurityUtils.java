package im.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityUtils {

	public static String userName() {
	   SecurityContext context = SecurityContextHolder.getContext(); 
	   Authentication authentication = context.getAuthentication(); 
	   String name = authentication.getName(); 
	  return name ; 
	}
}


