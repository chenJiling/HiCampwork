package tw.hicamp.product.service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.hicamp.product.model.OrderItem;
import tw.hicamp.product.model.Orders;
import tw.hicamp.product.model.Product;

@RestController
public class ECPayPaymentAPIController {

	@Autowired
	ECPayPaymentAPIService payService;
	@Autowired
	OrdersService ordersService;
	@Autowired
	ProductService productService;
	
	@PostMapping("/ecpayCheckout")
	public String ecpayCheckout(HttpSession session) {
		
//		Object memberNoObj = session.getAttribute("memberNo"); //
//		int memberNo = (int) memberNoObj; //
		
		Orders memberNewOrder = ordersService.findnewOrderByMember(1);
		Date orderDate= memberNewOrder.getOrderDate();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String payDate = ft.format(orderDate).toString();
//		System.out.println(payDate);
		String totalPrice = Integer.toString(memberNewOrder.getOrderTotalPrice());
		List<OrderItem> orderItems = memberNewOrder.getOrderItems();
		StringJoiner productNameJoiner = new StringJoiner(", ");
		
		for (OrderItem orderItem : orderItems) {
			
			Product product = productService.getProduct(orderItem.getProductNo());
			String productName= product.getProductName();
			productNameJoiner.add(productName);
		}
		
		String productNames = productNameJoiner.toString();
		
		String aioCheckOutALLForm = payService.ecpayCheckout(payDate, totalPrice, productNames);
		
		return aioCheckOutALLForm;
	}
}
