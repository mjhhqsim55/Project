package im.dao;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "uid")
	@SequenceGenerator( name = "sequenceGenerator", sequenceName = "user_st" ,  
	      allocationSize = 1 , initialValue = 10000  )
	@GeneratedValue(generator ="sequenceGenerator" )
	private Integer uid ; 

	@NotEmpty
	private String username ; 
	
	@Email
	private String eMail ; 
	
	private String password ; 
	
	private boolean isEnable = true ; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date registerDate = new Date() ;   
	
	@ManyToMany
	@JoinTable(name="USER_ROLE")
	private Set<Role> roles ; 
	
	@OneToOne(cascade = CascadeType.ALL)
	private ShopCar shopCar ; 

	public User() {
		
	}
	
	public User(String username, String eMail, String password) {
		super();
		this.username = username;
		this.eMail = eMail;
		this.password = password;
	}
	public User(String username, String eMail, String password , Date registerDate) {
		super();
		this.username = username;
		this.eMail = eMail;
		this.password = password;
		this.registerDate = registerDate ; 
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}


	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}


	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public ShopCar getShopCar() {
		return shopCar;
	}

	public void setShopCar(ShopCar shopCar) {
		this.shopCar = shopCar;
	}


	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", eMail=" + eMail + ", password=" + password
				+ ", isEnable=" + isEnable + ", registerDate=" + registerDate + ", roles=" + roles + ", shopCar="
				+ shopCar + "]";
	}

	

	

}