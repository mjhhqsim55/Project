package im.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import im.configuration.exception.UserDisableAuthenticationException;
import im.dao.Menu;
import im.dao.Role;
import im.dao.mapper.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserMapper userMapper;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		im.dao.User userNameValidate = userMapper.userNameValidate(username);
	
		UserDetails userDetails = null;
		if (userNameValidate == null) {
			throw new UsernameNotFoundException("没有此用户！"); 
		} else {
			if(userNameValidate.isEnable()) {
			    Set<Role> roles = userNameValidate.getRoles();
				List<GrantedAuthority> separatedStringToAuthorityList = SeparatedStringToAuthorityList(roles);
				userDetails = new User(userNameValidate.getUsername(),
						new BCryptPasswordEncoder().encode(userNameValidate.getPassword()),
						separatedStringToAuthorityList);
			}else {
				throw new UserDisableAuthenticationException("disable");
			}

		}
		return userDetails;
	}

	public List<GrantedAuthority> SeparatedStringToAuthorityList(Set<Role> roles) {
		StringBuffer authorityStr = new StringBuffer();
		if (roles != null) {
			for (Role role : roles) {
				authorityStr.append(role.getRname());
				authorityStr.append(",");
				Set<Menu> menus = role.getMenus();
				if (menus != null) {
					for (Menu menu : menus) {
						authorityStr.append(menu.getMname());
						authorityStr.append(",");
					}
				}
			}
		}
		int lastIndexOf = authorityStr.lastIndexOf(",");
		if (lastIndexOf == authorityStr.length() - 1) {
			authorityStr = new StringBuffer(authorityStr.substring(0, authorityStr.lastIndexOf(",")));
		}

	  return AuthorityUtils.commaSeparatedStringToAuthorityList(authorityStr.toString());
	}

}
