package tw.hicamp.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import tw.hicamp.member.service.MemberService;
import tw.hicamp.product.model.OrderItem;
import tw.hicamp.product.model.Orders;
import tw.hicamp.product.model.Product;
import tw.hicamp.product.model.ShoppingCart;
import tw.hicamp.product.service.OrdersService;
import tw.hicamp.product.service.ProductService;
import tw.hicamp.product.service.ShoppingCartService;

@Controller
public class OrderController {

	@Autowired
	private OrdersService oService;
	@Autowired
	private MemberService mService;
	@Autowired
	private ShoppingCartService sCartService;
	@Autowired
	private ProductService pService;

	// 訂單後台主頁
	@GetMapping("/orderHome")
	public String getAllOrders(Model m) {
		List<Orders> allOrders = oService.getAllOrders();
		m.addAttribute("allOrders", allOrders);
		return "product/orderHome";
	}

	// 新增訂單+訂單明細
	@ResponseBody
	@PostMapping("/product/addOrder")
	public String addOrder(HttpSession session, @RequestParam("orderName") String orderName,
			@RequestParam("orderPhone") int orderPhone, @RequestParam("orderShipAddress") String orderShipAddress,
			@RequestParam("orderMessage") String orderMessage, @RequestParam("orderTotalPrice") int orderTotalPrice,
			@RequestParam("orderPayWay") String orderPayWay, @RequestParam("orderShipping") String orderShipping, 
			@RequestParam("orderStatus") String orderStatus)
			throws IOException {

		Object memberNoObj = session.getAttribute("memberNo");
		if (memberNoObj != null) {
			int memberNo = (int) memberNoObj;
			// 取得當前時間
//			DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy ',' HH:mm:ss");
//			Date date = dateFormat.format(Calendar.getInstance().getTime());
			Date date = (Date) Date.from(Instant.now());

			Orders order = new Orders();
			order.setOrderDate(date);
			order.setOrderName(orderShipAddress);
			order.setOrderPhone(orderPhone);
			order.setOrderShipAddress(orderShipAddress);
			order.setOrderMessage(orderMessage);
			order.setOrderTotalPrice(orderTotalPrice);
			order.setOrderPayWay(orderPayWay);
			order.setOrderShipping(orderShipping);
			order.setOrderStatus("正常");
			oService.addOrder(order);
			
			List<ShoppingCart> memberCart = sCartService.getMemberCart(memberNo);
			ArrayList<OrderItem> newItemList = new ArrayList<>();
			for (ShoppingCart shoppingCart : memberCart) {
				OrderItem newitem = new OrderItem();
				newitem.setUnitPrice(shoppingCart.getProductPrice());
				newitem.setItemQuantity(shoppingCart.getItemQuantity());
				
				Product productItem = pService.getProduct(shoppingCart.getProductNo());
				newitem.setProductNo(productItem.getProductNo());
				
				
				newItemList.add(newitem);
			}
			
			Orders newOrder = oService.findnewOrderByMember(memberNo);
			
			
			
			
			
			
			
			oService.findnewOrderByMember(memberNo);
			
		}

		return "加入訂單成功";
	}

	// 更改訂單狀態
	@PostMapping("/product/updateStutas")
	public boolean updateOrderStutas(int orderNo, String stutas) {
		return oService.updateOrderStutas(orderNo, stutas);
	}

}
