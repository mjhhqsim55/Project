package im.configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan(basePackages = "im.dao.mapper")
public class ProjectConfiguration implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("index.html") ; 
//    	registry.addViewController("/login").setViewName("/login.html") ;  
    	registry.addViewController("/register").setViewName("register.html") ; 
//    	registry.addViewController("/activity").setViewName("/activity.html") ; 
//    	registry.addViewController("/prodc").setViewName("/prodc.html") ; 
//    	registry.addViewController("/prodc/data").setViewName("/prodc-data.html") ; 
//    	registry.addViewController("/user/manager").setViewName("/user-search.html") ; 
//    	registry.addViewController("/search").setViewName("/search.html") ; 
//    	registry.addViewController("/manager").setViewName("/manager.html") ; 
//    	registry.addViewController("/items").setViewName("/items.html") ; 
//    	registry.addViewController("/other").setViewName("/other.html") ; 
//    	registry.addViewController("/error").setViewName("/error/error.html") ; 
        registry.addViewController("/unauth").setViewName("unauth.html") ; 
//      registry.addViewController("/authorize").setViewName("/authorize.html") ; 
	}

}




