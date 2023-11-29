package im.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import im.dao.BuyItems;
import im.dao.BuyItemsRepository;
import im.dao.Prod;
import im.dao.ProdRepository;
import im.dao.ShopCar;
import im.dao.User;
import im.dao.UserRepository;
import im.utils.UserShopUtils;

@Service
public class UserShopService {

	@Autowired
	UserRepository userRepository ; 
	
	@Autowired
	ProdRepository prodRepository ; 
	
	
	@Autowired
	BuyItemsRepository buyItemsRepository ; 
	
	public ShopCar shopCar(int uid) {
		User user = userRepository.getReferenceById(uid);
		ShopCar shopCar = null ; 
		List<BuyItems> buyItems = null ; 
		if(user!=null) {
			shopCar = user.getShopCar() ; 
		    if(shopCar==null) {
		    	shopCar = new ShopCar() ;  
		        buyItems = new ArrayList<>() ; 
		    	
		   }else {
			   buyItems = shopCar.getBuyItems();
			   if(buyItems==null) {
				    buyItems = new ArrayList<>() ; 
				    
			   }
		   }
		    
		    shopCar.setBuyItems(buyItems);
	    	user.setShopCar(shopCar);
	    	User save = userRepository.save(user);
	    	shopCar = save.getShopCar() ; 
		}
	  return shopCar ; 
	}
	
	public int ProcdStack(int pid) {
		Prod prod = prodRepository.getReferenceById(pid); 
		int stack = 0 ; 
		if(prod != null) {
			stack = prod.getStock() ; 
		}
	  return stack ; 
	}
	
	public int shopCarProcd(int uid , int pid , boolean pay , int count) {
		
		int result = -1 ; 
		
		User userRep = userRepository.getReferenceById(uid) ; 
		
		if(userRep==null) {
			result = 0 ; 
		} else {
		    ShopCar shopCar = userRep.getShopCar() ;  
		    List<BuyItems> buyItems = null ; 
		    if(shopCar==null) {
		    	shopCar = new ShopCar() ;  
		    	buyItems = new ArrayList<>() ; 
		    	shopCar.setBuyItems(buyItems);
		    } else {
		        buyItems = shopCar.getBuyItems();  
		       if(buyItems==null) {
		    	   buyItems = new ArrayList<>() ; 
		    	   shopCar.setBuyItems(buyItems);
		       }
			}
		    
		    BuyItems buyItems2 = new BuyItems() ; 
		    // pay
		    Prod prod = prodRepository.getReferenceById(pid);
		    
		    
		    if(prod.getStock() >= count){
		    	if(pay) {
		    	  prod.setStock(prod.getStock()-count);
				   // prodRepository.flush() ; 
		    	}
				buyItems2.setPay(pay);
				buyItems2.setCount(count);
				buyItems2.setNumberItem(UserShopUtils.createNumberItem());
				buyItems2.setProd(prod);
				
				buyItemsRepository.save(buyItems2) ; 
				
				buyItems.add(buyItems2) ; 
				userRep.setShopCar(shopCar);
				//userRepository.flush();
				User save = userRepository.save(userRep); 
				if(isContainsBuyItems(save, buyItems2.getBid())) {
					result = 1 ; 
				}else{
					result = -1 ; 
				}; 
		    }else {
		    	// not stack  
		    	result = -10 ; 
		    	
		    }

		}
	 
	  return result ; 
	}
	
	public int shopCarProcdRemove(int uid , int bid) {
		
		int result = -1 ; 
		
		User userRep = userRepository.getReferenceById(uid) ; 
		
		if(userRep==null) {
			result = 0 ; 
		} else {
		    ShopCar shopCar = userRep.getShopCar();
		    BuyItems buyItems2 = null; 
		    boolean remove = false  ; 
			List<BuyItems> buyItems = shopCar.getBuyItems(); 
			if(buyItems != null) {
	            for(int i = 0 ; i < buyItems.size() ; i++) {
	            	BuyItems buyItems1 = buyItems.get(i); 
	            	if(buyItems1.getBid()==bid) {
	            		buyItems.remove(buyItems1) ; 
	            		remove = true  ; 
	            		buyItems2 = buyItems1 ; 
	            	}
	            }
			}
			if(buyItems2 != null) {
				User save = userRepository.save(userRep); 
				if(remove) {
					if(isContainsBuyItems(save, buyItems2.getBid())) {
						result = -1 ; 
					}else{
						result = 1 ; 
					}; 
				}
			}else {
				// not bid
				result = -11 ; 
			}
		}
	 
	  return result ; 
	}

	
	public boolean isContainsBuyItems(User save ,int bid) {
		boolean result = false ; 
		ShopCar shopCar = save.getShopCar();
		List<BuyItems> buyItems = shopCar.getBuyItems();
		if(!Objects.isNull(save)&&!Objects.isNull(shopCar)&&!Objects.isNull(buyItems)) {
			for(BuyItems items : buyItems) {
				if(!Objects.isNull(items)) {
					if(items.getBid()==bid) {
						result = true ; 
						break ; 
					}
				}
			}
        }
		return result ; 
	}
	
}
