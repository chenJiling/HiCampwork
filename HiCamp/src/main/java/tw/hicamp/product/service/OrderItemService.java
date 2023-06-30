package tw.hicamp.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.hicamp.product.model.OrderItem;
import tw.hicamp.product.model.OrderItemRepository;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository oItemRepo;
	
	//取訂單明細
	public OrderItem getOrderItem(int orderNo) {
		return oItemRepo.findItemByOrderNo(orderNo);
	}

}
