package im.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import im.dao.ImageEntity;
import im.dao.Prod;

public interface ActivityMapper {

	public List<Prod> prods(@Param("aid") int aid ) ; 
	
	public List<ImageEntity> images(@Param("pid") int pid) ;  
	
}
