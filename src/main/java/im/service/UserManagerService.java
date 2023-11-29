package im.service;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import im.dao.Menu;
import im.dao.Role;
import im.dao.RoleRepository;
import im.dao.User;
import im.dao.UserRepository;
import im.dao.mapper.UserMapper;
import im.utils.PageUtils;

@Service
public class UserManagerService {

	@Autowired
	UserMapper userMapper ; 
	
	@Autowired
	UserRepository userRepository ; 
	
	@Autowired
	RoleRepository roleRepository ; 

	public UserManagerService() {} ; 
	
	public User rootUser() {
		User root = userRepository.getReferenceById(0) ;  
		for(Role role : root.getRoles()) {
			Set<Menu> menus = role.getMenus();
			for(Menu menu : menus) {
				menu.getMid() ; 
			}
		}
	  return root ; 
	}
	
	public int enableUserRole(int uid , int rid) {
		int result = 1 ;  
		
		try {
			User user = userRepository.getReferenceById(uid) ; 
			Role role = roleRepository.getReferenceById(rid) ; 
		    user.getRoles().add(role);
		    
		    User save = userRepository.save(user); 
		    boolean contains = save.getRoles().contains(role) ; 
		    if(contains) {
		    	result = 1 ; 
		    }else {
		    	result = -1 ; 
		    }
		}catch(Exception e) {
			result = -1 ;  
		}
		
	  return result ; 
	}
	
	public int disableUserRole(int uid , int rid) {
        int result = -1 ;  
		
		try {
			User user = userRepository.getReferenceById(uid) ; 
			Role role = roleRepository.getReferenceById(rid) ; 
		    user.getRoles().remove(role) ; 
		    
		    User save = userRepository.save(user) ; 
		    boolean contains = save.getRoles().contains(role) ; 
		    if(contains) {
		    	result = -1 ; 
		    }else {
		    	result = 2 ; 
		    }
		}catch(Exception e) {
			result = -1 ;  
		}
		
	  return result ; 
	}
	
	
	public User userAuth(int uid) {
		User user = userRepository.getReferenceById(uid); 
	  return userRepository.save(user) ; 
	}
	
	public User user(int uid , boolean enable) {
		User user = userRepository.getReferenceById(uid); 
		user.setEnable(enable);
	  return userRepository.save(user) ; 
	}

	public PageInfo<User> users(int uid , String username , int pageNum){
        PageHelper.startPage(pageNum, PageUtils.PAGE_SIZE); 
		List<User> userSearch = userMapper.userSearch(uid, username);
	    PageInfo<User> pageInfo = new PageInfo<>(userSearch , 5) ; 
	   return pageInfo ; 
	}
	
}
