package im.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import im.dao.Prod;
import im.dao.mapper.ActivityMapper;

@Service
public class ActivityService {

	@Autowired
	ActivityMapper activityMapper  ; 
	
	public PageInfo<Prod> prods(int id , int pageNum){
		PageHelper.startPage(pageNum, 8) ; 
		List<Prod> prods = activityMapper.prods(id); 
		PageInfo<Prod> pageInfo = new PageInfo<>(prods, 5)  ; 
	   return pageInfo ; 
	}; 
	
}
