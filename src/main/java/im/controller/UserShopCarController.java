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

import im.dao.ShopCar;
import im.dao.User;
import im.service.UserShopService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserShopCarController {

	@Autowired
	UserShopService userShopService ; 
	
	
	@GetMapping("/shop/car")
	public String shopCar(HttpSession session , Map<String,Object> map) {
        
		Object attribute = session.getAttribute("user") ;  
		if(attribute != null) {
          User user = (User)attribute ; 
          Integer uid = user.getUid();
          ShopCar shopCar = userShopService.shopCar(uid); 
        
          map.put("shopCar", shopCar) ; 
        }else {
        	map.put("shopCar", null) ; 
        }
		
	  return "shopCar.html" ; 
	}
	
	
	@GetMapping("/remove/items/{id}")
	public String removeBuyItems(HttpSession session , @PathVariable("id") int bid) {
		
		String result = "error/error.html" ; 
		
		Object attribute = session.getAttribute("user") ;  
		if(attribute != null) {
          User user = (User)attribute ; 
          Integer uid = user.getUid();
          int removeBuyItems = userShopService.shopCarProcdRemove(uid, bid); 
          if(removeBuyItems == 1) {
        	  result = "redirect:/user/shop/car" ; 
          }
       }
	  return result ; 
	}
	
	@ResponseBody
	@PostMapping("/procd/stack")
	public int ProcdStack(@RequestParam("pid") int pid) {
	   return userShopService.ProcdStack(pid) ; 
	}
	
	
	@ResponseBody
	@PostMapping("/shop/procd/{m}")
	public int shopProcd(@PathVariable("m") int m  , @RequestParam("pid") int pid, 
			@RequestParam("count") int count , HttpSession session) {
		int result = -1 ; 
		
		Object attribute = session.getAttribute("user") ;  
		if(attribute != null) {
          User user = (User)attribute ; 
          Integer uid = user.getUid();
          if(m==0) {
        	  result = userShopService.shopCarProcd(uid, pid, false, count) ; 
          }else if(m==1) {
        	  result = userShopService.shopCarProcd(uid, pid, true, count) ; 
          }
           
		}else {
			result = -1 ; 
		}
		
	  return result ; 
	}
	
}
