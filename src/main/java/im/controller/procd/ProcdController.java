package im.controller.procd;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import im.dao.Category;
import im.dao.CategoryRepository;
import im.dao.Prod;
import im.service.ProcdService;

@Controller
public class ProcdController {

	@Autowired
	CategoryRepository categoryRepository ; 
	
	@Autowired
	ProcdService procdService ; 
	
	@ResponseBody
	@GetMapping("/category")
	public String CategoryControl() {
		Category category = categoryRepository.getReferenceById(5); 
		System.out.println("category: "+category);
	  return "Category" ; 
	}

	@GetMapping("/prodc/{id}/{pageNum}")
	public String procd(@PathVariable("id") int cid ,
			@PathVariable("pageNum") int pageNum , Map<String , Object> map) {
	     PageInfo<Prod> prods = procdService.prods(cid , pageNum);
		 map.put("prods", prods) ; 
		 map.put("cid", cid) ; 
		 
	   return "prodc.html" ; 
	}

	@GetMapping("/prodc/data/{id}")
	public String procdData(@PathVariable("id") int pid , Map<String,Object> map) {
		Prod prod = procdService.prod(pid) ; 
	    map.put("prod", prod) ; 
	   return "prodc-data.html" ; 
	}
	
 
	@PostMapping("/search/{pageNum}")
	public String search(@Param("search") String search , 
			@PathVariable("pageNum") int pageNum , Map<String,Object> map) {
		 searchHandler(search,pageNum,map) ; 
	   return "search.html" ; 
	}
	
	@GetMapping("/search/{search}/{pageNum}")
	public String search1(@PathVariable(value="search" , required = false) String search , 
			@PathVariable("pageNum") int pageNum , Map<String,Object> map) {
		  searchHandler(search,pageNum,map) ; 
	    return "search.html" ; 
	}
	
	public void searchHandler(String search , int pageNum , Map<String,Object> map) {
		if(search.isBlank()) {
			search = "_" ; 
		}
		PageInfo<Prod> prods = procdService.search(search, pageNum); 
		map.put("prods", prods) ; 
		map.put("search", search) ; 
	}
	
}





