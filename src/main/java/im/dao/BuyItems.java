package im.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "buyItems")
public class BuyItems  implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bid")
	@SequenceGenerator( name = "sequenceGenerator", sequenceName = "buyItems_st" ,  
	      allocationSize = 1 , initialValue = 10000  )
	@GeneratedValue(generator ="sequenceGenerator" )
	private int bid ; 
	
	private String numberItem ; 
	
	private int count ; 
	
	private boolean isPay ; 
	
	private Date date = new Date(); 
	
	@ManyToOne
	private Prod prod ; 

	public BuyItems() {
		// TODO Auto-generated constructor stub
	}
	
	public BuyItems(String numberItem) {
		this.numberItem = numberItem ; 
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getNumberItem() {
		return numberItem;
	}

	public void setNumberItem(String numberItem) {
		this.numberItem = numberItem;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Prod getProd() {
		return prod;
	}

	public void setProd(Prod prod) {
		this.prod = prod;
	}
	
	public boolean isPay() {
		return isPay;
	}

	public void setPay(boolean isPay) {
		this.isPay = isPay;
	}
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(bid, count, date, isPay, numberItem, prod);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BuyItems other = (BuyItems) obj;
		return bid == other.bid  ; 
	}

	public String toString() {
		return "BuyItems [bid=" + bid + ", numberItem=" + numberItem + ", count=" + count + ", isPay=" + isPay
				+ ", date=" + date + ", prod=" + prod + "]";
	}

	
}
