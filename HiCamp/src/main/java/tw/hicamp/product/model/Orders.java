package tw.hicamp.product.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import tw.hicamp.member.model.Member;

@Entity @Table(name = "orders")
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderNo;

	private Date orderDate;
	
	private String orderName;
	private int orderPhone;
	private String orderShipAddress;
	private String orderMessage;
	
	private int orderTotalPrice;
	private String orderPayWay;
	private String orderShipping;
	
	private String orderStatus;
	
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "memberNo")
	private Member member;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	


	

}