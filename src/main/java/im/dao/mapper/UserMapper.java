package im.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import im.dao.User;

@Mapper
public interface UserMapper {

	public User userNameValidate(@Param("username") String username) ; 
	
	public User user(@Param("username") String username) ; 
	
	public List<User> userSearch(@Param("uid") int uid,
			                       @Param("username") String username) ; 

}

