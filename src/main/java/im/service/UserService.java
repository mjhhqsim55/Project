package im.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import im.dao.Role;
import im.dao.RoleRepository;
import im.dao.User;
import im.dao.UserRepository;
import im.dao.mapper.UserMapper;

@Service
public class UserService {

	public static final int RESULT_USER_1 = 1  ; 
	public static final int RESULT_USER_0 = 0  ; 
	
	public static final int RESULT_USER_REGISTER_OK = 11  ; 
	public static final int RESULT_USER_CHANGE_OK = 22  ; 
	
	@Autowired
	UserMapper userMapper ; 

	@Autowired
	UserRepository userRepository ; 
	
	@Autowired
	RoleRepository roleRepository ; 
	
	public List<User> userSearch(){
		
	  return null ; 
	}
	
	public int registerUser(User user) { 
	    int result = 0 ; 
		User user2 = userMapper.user(user.getUsername()); 
		if(user2 == null) {
			Role role = roleRepository.getReferenceById(1002); 
			Set<Role> roles = new HashSet<>() ; 
			roles.add(role) ;   
			user.setRoles(roles);
			user2 = userRepository.save(user) ; 
		    result = RESULT_USER_REGISTER_OK ; 
		}else {
		  result = RESULT_USER_1 ; 
		}
	  return result ; 
	}
	
	public Map<String ,Object> changeUser(User source , User dest) {
		int result = RESULT_USER_CHANGE_OK ; 
		Map<String , Object> map = new HashMap<>() ; 
		
		String eMail = dest.geteMail();
		String username = dest.getUsername();
		String password = dest.getPassword();
         
		if(!ObjectUtils.isEmpty(username)||!ObjectUtils.isEmpty(eMail)||
				!ObjectUtils.isEmpty(password)) {
		 
			User user2 = userMapper.user(username); 
			if(user2==null) {
				user2 = userMapper.user(source.getUsername()); 
				if(user2 != null) {
					User user = userRepository.getReferenceById(user2.getUid()); 
					
					if(!ObjectUtils.isEmpty(eMail)) {
		              user.seteMail(eMail);
					}
					
					
					if(!ObjectUtils.isEmpty(password)) {
						user.setPassword(password);
					}
					
					 
					if(!ObjectUtils.isEmpty(username)) {
						user.setUsername(username);
					}
					
				   map.put("user", userRepository.save(user)) ; 
				   result = RESULT_USER_CHANGE_OK ; 
				}else {
					result = RESULT_USER_0 ; 
				}
			}else {
				result = RESULT_USER_1 ; 
			}
			
	    }
		
		map.put("result", result) ; 
		
	  return map ; 
	}
	
}

