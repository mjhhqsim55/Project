package im.dao;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "imageEntity")
public class ImageEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator( name = "sequenceGenerator", sequenceName = "imageEntity_st" ,  
	      allocationSize = 1 , initialValue = 10  )
	@GeneratedValue(generator ="sequenceGenerator" )
	private int id ; 
	
	private String imageName ; 
	
	private String path ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
 
	public String toString() {
		return "ImageEntity [id=" + id + ", imageName=" + imageName + ", path=" + path + "]";
	}


}
