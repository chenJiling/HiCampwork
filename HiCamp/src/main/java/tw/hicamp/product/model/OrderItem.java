package tw.hicamp.product.model;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity @Table(name = "orderItem")
@Data
public class OrderItem {

	private int itemQuantity;
	private int unitPrice;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderNo")
	private Orders order;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productNo")
	private Product product;
	

	

	

	

}