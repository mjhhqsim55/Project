package im.dao;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L ; 

	@Id
	@Column(name = "rid")
	private int rid ; 
	
	private String rname ; 
	
	private String name ; 
	
	@ManyToMany
	@JoinTable(name="ROLE_MENU")
	private Set<Menu> menus ;

	
	public Role() {
		 
	}
	
	public Role(int rid) {
	  this.rid = rid ; 
	}
	
	public Role(int rid , String rname) {
		  this.rid = rid ; 
		  this.rname =rname ; 
	}
	
	
	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}


	public String toString() {
		return "Role [rid=" + rid + ", rname=" + rname + ", name=" + name + ", menus=" + menus + "]";
	} 
	
}

