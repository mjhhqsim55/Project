package im.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "prod")
public class Prod implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pid")
	@SequenceGenerator( name = "sequenceGenerator", sequenceName = "prod_st" ,  
	      allocationSize = 1 , initialValue = 100  )
	@GeneratedValue(generator ="sequenceGenerator" )
	private int pid ; 
	
	private String name ; 
	
	private int stock ; 
	
	private double price ; 
	
	private String presentation ; 
	
	@ManyToMany
	@JoinTable(name="PROD_IMAGE")
	private List<ImageEntity> images ; 

	private Category category ; 
	
    public Prod() { } 
	
    
    
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}



	public List<ImageEntity> getImages() {
		return images;
	}



	public void setImages(List<ImageEntity> images) {
		this.images = images;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}


	public String toString() {
		return "Prod [pid=" + pid + ", name=" + name + ", stock=" + stock + ", price=" + price + ", presentation="
				+ presentation + ", images=" + images + ", category=" + category + "]";
	}


	
}
