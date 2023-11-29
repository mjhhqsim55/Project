package im.controller.activity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.pagehelper.PageInfo;

import im.dao.Prod;
import im.service.ActivityService;

@Controller
public class ActivityController {

	@Autowired
	ActivityService activityService ; 
	
	@GetMapping("/activity/{id}/{pageNum}")
	public String activity(@PathVariable("id") int id , @PathVariable("pageNum") int pageNum ,
			Map<String,Object> map) {
		 
		PageInfo<Prod> prods = activityService.prods(id, pageNum);
		map.put("prods", prods);
		
	  return "activity.html" ; 
	}
	
}
