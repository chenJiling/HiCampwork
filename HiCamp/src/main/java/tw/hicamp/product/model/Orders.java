package tw.hicamp.product.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import tw.hicamp.member.model.Member;

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
	private String orderInfo;
	private String orderMessage;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "memberNo")
	private Member member;
	

	


	

}