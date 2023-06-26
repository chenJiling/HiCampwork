package tw.hicamp.product.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity @Table(name = "orders")
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderNo;

	private String orderType;
	private Date orderDate;
	private int orderTotalAmount;
	private String orderStatus;
	private String orderShipping;
	private String orderPayWay;
	private String orderShipAddress;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "memberNo")
//	private Member member;
	

	


	

}