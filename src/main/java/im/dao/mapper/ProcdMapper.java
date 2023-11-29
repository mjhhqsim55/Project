package im.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import im.dao.ImageEntity;
import im.dao.Prod;

public interface ProcdMapper {

	public List<Prod> SearchProds(@Param("search") String search) ; 
	
	public List<Prod> prods(@Param("cid") int cid) ; 
	
	public List<ImageEntity> imageEntitys(@Param("pid") int pid) ; 
	
}

