package im.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import im.dao.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController { 

	@Autowired
	UserRepository userRepository ; 

	@GetMapping("/message")
    public String userMessage() {   
	  return "user-message.html" ; 
    }
	
	@GetMapping("/message/change/page")
    public String userMessageChangePage() {   
	  return "user-change.html" ; 
    }

}




