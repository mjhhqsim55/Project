package im.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import im.dao.User;
import im.service.UserManagerService;

@Controller
@RequestMapping("/user/manager")
public class UserManagerController {

	@Autowired
	UserManagerService managerService  ; 

	 
	@GetMapping("/search")
	public String managerMain() { 
		return "user-manager.html" ; 
	}

	@ResponseBody
	@PostMapping("/user/role")
	public int authorizeUserRole(@RequestParam("uid") int uid ,  
			@RequestParam("rid") int rid , @RequestParam(value="role" , required = false) String role) {
		int result = 1 ; 
		 if(uid!=0) {
		     
			 if(role != null && role.equals("on")) {
				 result = managerService.enableUserRole(uid, rid) ; 
			 }else {
				 result = managerService.disableUserRole(uid, rid) ; 
			 }
			
		 }else if(uid==0) {
			 result = 0 ; 
		 }
		return result ; 
	}


	public void authorizeHandler(int uid , int currentPage , String search , Map<String ,Object> map) { 
		 
		User userAuth = managerService.userAuth(uid); 
		
		map.put("user", userAuth) ; 
		map.put("root", managerService.rootUser()) ; 
		map.put("search", search) ; 
		map.put("currentPage", currentPage) ; 
	}
	
	@GetMapping("/authorize/{uid}/{currentPage}/{search}")
	public String authorize(@PathVariable("uid") int uid ,
			@PathVariable("currentPage") int currentPage ,
			@PathVariable("search") String search , Map<String ,Object> map) { 
		authorizeHandler(uid,currentPage,search,map) ; 
		return "authorize.html" ; 
	}
	
	@GetMapping("/authorize/{uid}/{currentPage}/")
	public String authorizeAllUsers(@PathVariable("uid") int uid ,
			@PathVariable("currentPage") int currentPage , Map<String ,Object> map) { 
		authorizeHandler(uid,currentPage,"",map) ; 
		return "authorize.html" ; 
	}

	
	
	@PostMapping("/main/{pageNum}")
	public String manager(@RequestParam String search , @PathVariable("pageNum") int pageNum ,  
			Map<String,Object> map) { 
		managerHandler(search, pageNum, map) ; 
	  return "manager.html" ; 
	}
	
	@GetMapping("/main/{pageNum}/{search}")
	public String managerOther(@PathVariable("search") String search , 
			        @PathVariable("pageNum") int pageNum ,  
			Map<String,Object> map) { 
		managerHandler(search, pageNum, map) ; 
	  return "manager.html" ; 
	}
	
	@GetMapping("/main/{pageNum}/")
	public String managerAll(@PathVariable("pageNum") int pageNum ,  
			Map<String,Object> map) { 
		managerHandler("", pageNum, map) ; 
	  return "manager.html" ; 
	}
	

	@ResponseBody
	@PostMapping("/user/enable") 
    public int UserEnable(@RequestParam("uid") int uid , 
    		@RequestParam(value= "user" , required = false) String is ) {
    
    	int result = 0 ; 
    	if(uid==0) {
    		result = 0 ; 
    	}else {
    		boolean isEnable = false  ; 
    		if("on".equals(is)) {
    			isEnable = true ; 
    		}
    		User user = null ; 
    		try {
    		 user = managerService.user(uid , isEnable) ; 
    		}catch(Exception e) {
    			result = -1 ; 
    		}
    	
    		if(user != null) {
    			if(user.isEnable()) {
        	    	result = 1 ; 
        	    }else {
        	    	result = 2 ; 
        	    }
    		}
    	 }
    	
    	return result ; 
    }

	
	
	public void managerHandler(String search ,int pageNum ,Map<String,Object> map) {
		PageInfo<User> pageInfo = null ; 
		try { 
		  int key = Integer.parseInt(search); 
		  pageInfo = managerService.users(key, search , pageNum); 
		}catch(NumberFormatException e) {
		  pageInfo = managerService.users(-1, search , pageNum ); 
		}
		map.put("Userdata", pageInfo) ; 
		map.put("search", search) ; 
		map.put("currentPage", pageNum) ; 
	}
	
}




