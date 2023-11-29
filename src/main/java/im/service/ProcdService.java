package im.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import im.dao.Prod;
import im.dao.ProdRepository;
import im.dao.mapper.ProcdMapper;

@Service
public class ProcdService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	ProcdMapper procdMapper ; 
	
	@Autowired
	ProdRepository prodRepository ;  

	public PageInfo<Prod> search(String search , int pageNum){
		
		PageHelper.startPage(pageNum, 5) ; 
		List<Prod> prods = procdMapper.SearchProds(search); 
		
 		PageInfo<Prod> pageInfo = new PageInfo<>(prods, 5) ; 

	   return pageInfo ; 
	};     
	
	
	public Prod prod(int pid) {
		 Prod prod = prodRepository.getReferenceById(pid); 
		 prod.getImages() ; 
		return prod ; 
	}
	
	public PageInfo<Prod> prods(int cid , int pageNum){
		PageHelper.startPage(pageNum, 5) ; 
		List<Prod> prods = procdMapper.prods(cid); 
		PageInfo<Prod> pageInfo = new PageInfo<>(prods, 5) ; 
	   return pageInfo ; 
	};     
}
