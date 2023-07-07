package tw.hicamp.product.service;


import java.util.List;
import java.util.Map;

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
	
	//分析類別
	public List<Map<String, Integer>> findQuantityByType(){
		return oItemRepo.findtotalQuantityGroupByType();
	}

}
