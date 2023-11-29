package im.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import im.dao.Activity;
import im.dao.Banner;
import im.dao.Category;
import im.service.IndexService;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	IndexService indexService ;  
	
	@GetMapping("/")
	public String indexPage(Map<String , Object> map) {
		List<Activity> activitys = indexService.activitys();
		List<Banner> banners = indexService.banners();
		List<Category> categorys = indexService.categorys();
		
		map.put("activitys", activitys) ; 
		map.put("banners", banners) ; 
		map.put("categorys", categorys) ; 
	
		return "index.html" ; 
	}
	
}
