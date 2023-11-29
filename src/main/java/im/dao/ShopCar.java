package im.dao;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "shopCar")
public class ShopCar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator( name = "sequenceGenerator", sequenceName = "shopCar_st" ,  
	      allocationSize = 1 , initialValue = 10000  )
	@GeneratedValue(generator ="sequenceGenerator" )
	private int id ; 
	
	@OneToMany
	private List<BuyItems> buyItems ; 

	public List<BuyItems> getBuyItems() {
		return buyItems;
	}

	public void setBuyItems(List<BuyItems> buyItems) {
		this.buyItems = buyItems;
	}

	public String toString() {
		return "ShopCar [id=" + id + ", buyItems=" + buyItems + "]";
	}
	
}

