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
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cid")
	@SequenceGenerator( name = "sequenceGenerator", sequenceName = "category_st" ,  
	      allocationSize = 1 , initialValue = 10  )
	@GeneratedValue(generator ="sequenceGenerator" )
	private int cid ; 
	
	private String categoryName ;
	
	
	@OneToMany
	private List<Prod> prods ; 

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Prod> getProds() {
		return prods;
	}

	public void setProds(List<Prod> prods) {
		this.prods = prods;
	}


	public String toString() {
		return "Category [cid=" + cid + ", categoryName=" + categoryName + ", prods=" + prods + "]";
	}

 
	
	
	
 
}
