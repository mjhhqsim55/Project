package im.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import im.dao.Activity;
import im.dao.ActivityRepository;
import im.dao.Banner;
import im.dao.BannerRepository;
import im.dao.Category;
import im.dao.CategoryRepository;

@Service
public class IndexService {

	@Autowired
	ActivityRepository activityRepository ; 
	
	@Autowired
	BannerRepository bannerRepository ; 
	
	@Autowired
	CategoryRepository categoryRepository ; 
	
	public List<Activity> activitys(){
		return activityRepository.findAll() ; 
	}; 
	
	public List<Banner> banners(){
		return bannerRepository.findAll() ; 
	}; 
	
	public List<Category> categorys(){
		return categoryRepository.findAll() ; 
	}; 
	
}

