package im.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import im.dao.User;
import im.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
//@SessionAttributes({"user"})
public class UserSessionController {

	public static final int ACTION_REGISTER = 1 ; 
	public static final int ACTION_LOGIN = 2 ; 
	public static String PARAM_REGISTER = "register" ; 
	public static String PARAM_LOGIN = "login" ; 
	
	@Autowired
	UserService userService ; 
	
	@ModelAttribute
	public void userData(@RequestParam(name = "username" , 
	   required = false) String username , Map<String, Object> map) {
		if(username != null) {
		//	User user = new User() ; 
			//map.put("user", user) ; 
		//	System.out.println(username);
		}
	}
	
	@GetMapping("/success")
	public String success(User user , Map<String,Object> map , 
			HttpServletRequest request){
		if(map.get(UserSessionController.PARAM_REGISTER)==null) {
	      map.put(UserSessionController.PARAM_LOGIN, UserSessionController.ACTION_LOGIN) ;
		}
	  return "success.html" ; 
	}	
	
	@GetMapping("/login")
	public String login(Map<String ,Object> map) {
//		 Enumeration<String> attributeNames = request.getAttributeNames(); 
//		 for( ;attributeNames.hasMoreElements();) {
//			 String str = attributeNames.nextElement() ; 
//			 System.out.println(str+":"+request.getParameter(str));
//		 }
		map.put(""+PARAM_LOGIN, ACTION_LOGIN) ; 
	  return "login.html" ; 
	}

	@PostMapping("/register/prod")
	public String register(@Valid User user , Errors errors , Map<String ,Object> map) { 
		 
		if(ValidUserNameEmailErrorProced(errors,map , true)) { 
		   return "register" ; 
		 }
		 
		 int registerUser = userService.registerUser(user); 
		 if(registerUser==UserService.RESULT_USER_1) {
			 map.put("RESULT_USER_1", UserService.RESULT_USER_1) ; 
			 return "register" ; 
		 }else if(registerUser==UserService.RESULT_USER_REGISTER_OK) {
			 map.put(""+PARAM_REGISTER, ACTION_REGISTER) ; 
		 }
	   return "success.html" ; 
	}

	@PostMapping("/user/message/change/procd")
    public String userMessageChangeProcd(@Valid User user , Errors errors , 
    		Map<String ,Object> map , HttpSession session) { 
		
		if(ValidUserNameEmailErrorProced(errors , map , false)) {
		  return "user-change.html" ; 
		}
		
		Object attribute = session.getAttribute("user"); 
		if(attribute!=null) {
			 User user2 = (User)attribute ; 
			 Map<String, Object> changeUser = userService.changeUser(user2, user); 
			 Integer integer = (Integer)changeUser.get("result");
			if(integer==UserService.RESULT_USER_0) {
				 map.put("RESULT_USER_0", UserService.RESULT_USER_0) ; 
			   return "user-change.html" ; 
			 }else if(integer==UserService.RESULT_USER_CHANGE_OK) {
				 Object object = changeUser.get("user");
				 if(object!=null) {
					 User userObject = (User)object ; 
					 String username = userObject.getUsername(); 
                     if(username!=null) {
                    	 user2.setUsername(username) ; 
                     }
                     
                     String eMail = userObject.geteMail(); 
                     if(eMail!=null) {
                    	 user2.seteMail(eMail) ; 
                     }

                     session.setAttribute("user" , user2) ; 
					 
				 }
			 }else if(integer==UserService.RESULT_USER_1) {
					 map.put("RESULT_USER_1", UserService.RESULT_USER_1) ; 
				    return "user-change.html" ; 
			}
		}
		
	  return "user-message.html" ; 
    }

	@GetMapping("/user/search")
	public String UserSearch() {
		
		return "" ; 
	}
	
	
	
    boolean ValidUserNameEmailErrorProced(Errors errors , Map<String ,Object> map , boolean isValid) {
		boolean result = false ; 
		if(errors.getFieldErrorCount()>0) {
			result = true ; 
			 List<FieldError>  errors1 = errors.getFieldErrors(); 
			 for(FieldError error : errors1) {
				 if(error.getCodes()[0].equals("NotEmpty.user.username")) {
					 if(isValid) {
					    map.put("NotEmpty_User_Username", error.getDefaultMessage()) ; 
					 }else {
						 result = false ; 
					 }
				 }else if(error.getCodes()[0].equals("Email.user.eMail")) {
						map.put("Email_User_EMail", error.getDefaultMessage()) ; 
				 }
			 }
		  
		}
	  return result ; 
	}
   
    
    

}





