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
@Table(name = "activity")
public class Activity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator( name = "sequenceGenerator", sequenceName = "activity_st" ,  
	      allocationSize = 1 , initialValue = 10  )
	@GeneratedValue(generator ="sequenceGenerator" )
	private int id ; 
	
	private String name ; 
	
	@OneToMany
	private List<Prod> prods  ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Prod> getProds() {
		return prods;
	}

	public void setProds(List<Prod> prods) {
		this.prods = prods;
	}


	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", prods=" + prods + "]";
	} 
	
	
	
}
