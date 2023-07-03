package tw.hicamp.product.model;

import java.util.*;

import lombok.Data;

@Data
public class OrderDTO {
	
	private String memberName;
	private String memberEmail;
	private String memberPhone;
	
	private String orderDate;
	private String orderName;
	private String orderPhone;
	private String orderShipAddress;
	private String orderMessage;
	private int orderTotalPrice;
	private String orderPayWay;
	private String orderShipping;
	
	private List<OrderItemDTO> orderItemDTO;
	
}
