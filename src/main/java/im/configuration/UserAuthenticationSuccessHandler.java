package im.configuration;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import im.dao.Role;
import im.dao.User;
import im.dao.mapper.UserMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	UserMapper userMapper ; 
	
	public UserAuthenticationSuccessHandler(UserMapper userMapper) {
		this.userMapper = userMapper ; 
	}

  	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
  			Authentication authentication) throws ServletException, IOException {
        super.onAuthenticationSuccess(request, response, authentication);
  	  HttpSession session = request.getSession(); 
  	  Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
  	  User user = userMapper.user(authentication.getName());
  	  
  	  // 是否拥有MANAGER
  	  GrantedAuthority authority = new SimpleGrantedAuthority("MANAGER") ; 
  	  if(authorities.contains(authority)) {
  		  Set<Role> roles = new HashSet<>() ; 
  		  roles.add(new Role(1004 , "MANAGER")) ; 
  		  user.setRoles(roles); 
  	  }
  	  session.setAttribute("user", user); 
     }	
  
}

