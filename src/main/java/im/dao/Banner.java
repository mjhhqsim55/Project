package im.dao;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "banner")
public class Banner implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator( name = "sequenceGenerator", sequenceName = "banner_st" ,  
	      allocationSize = 1 , initialValue = 10  )
	@GeneratedValue(generator ="sequenceGenerator" )
	private int id ; 
	
	@OneToOne
	private Prod prod ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Prod getProd() {
		return prod;
	}

	public void setProd(Prod prod) {
		this.prod = prod;
	}


	public String toString() {
		return "Banner [id=" + id + ", prod=" + prod + "]";
	} 
	
	
	
}
