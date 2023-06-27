package tw.hicamp.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tw.hicamp.member.model.Member;
import tw.hicamp.member.service.MemberService;
import tw.hicamp.product.model.Orders;
import tw.hicamp.product.model.Product;
import tw.hicamp.product.model.ProductPicture;
import tw.hicamp.product.service.OrdersService;

@Controller
public class OrderController {

	@Autowired
	private OrdersService oService;
	@Autowired
	private MemberService mService;

	// 訂單後台主頁
	@GetMapping("/orderHome")
	public String getAllOrders(Model m) {
		List<Orders> allOrders = oService.getAllOrders();
		m.addAttribute("allOrders", allOrders);
		return "product/orderHome";
	}

	// 新增訂單
	@ResponseBody
	@PostMapping("/product/addOrder")
	public String addOrder(@RequestParam("memberNo") int memberNo,
								@RequestParam("orderType") String orderType, 
								@RequestParam("orderTotalAmount") int orderTotalAmount,
								@RequestParam("orderStatus") String orderStatus,
								@RequestParam("orderShipping") String orderShipping,
								@RequestParam("orderPayWay") String orderPayWay,
								@RequestParam("orderShipAddress") String orderShipAddress)
			throws IOException {
		
		Member member = mService.findByNo(memberNo);
		List<Orders> newOrder = new ArrayList<>();
		
		Orders order = new Orders();
		order.setOrderType(orderType);
		order.setOrderTotalAmount(orderTotalAmount);
		order.setOrderStatus(orderStatus);
		order.setOrderShipping(orderShipping);
		order.setOrderPayWay(orderPayWay);
		order.setOrderShipAddress(orderShipAddress);
		
		newOrder.add(order);
		member.setOrders(newOrder);
		oService.addOrder(order);
		
		return "加入訂單成功"; 
	}
	
	@PostMapping("/product/updateStutas")
	public boolean updateOrderStutas(int orderNo, String stutas) {
		return oService.updateOrderStutas(orderNo, stutas);
	}


}
