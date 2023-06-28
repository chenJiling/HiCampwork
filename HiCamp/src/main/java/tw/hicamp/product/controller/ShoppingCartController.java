package tw.hicamp.product.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.aspectj.weaver.AjAttribute.MethodDeclarationLineNumberAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import tw.hicamp.member.model.Member;
import tw.hicamp.member.service.MemberService;
import tw.hicamp.product.model.CartDTO;
import tw.hicamp.product.model.Product;
import tw.hicamp.product.model.ShoppingCart;
import tw.hicamp.product.service.ProductService;
import tw.hicamp.product.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService sCartService;
	@Autowired
	private ProductService pService;
	@Autowired
	private MemberService mService;
	
	//新增購物車
	@ResponseBody
	@PostMapping("/shoppingCart/addcart")
	public String addCart(@RequestParam("memberNo") int memberNo,
						@RequestParam("productNo") int productNo,
						@RequestParam("productPrice") int productPrice,
						@RequestParam("itemQuantity") int itemQuantity) {
		
		ShoppingCart newCart = new ShoppingCart();
		newCart.setItemQuantity(itemQuantity);
		newCart.setProductPrice(productPrice);
		newCart.setMemberNo(memberNo);
		newCart.setProductNo(productNo);
		
		sCartService.addCart(newCart);
		return "新增購物車成功";
		
	}
	//刪除item
	@ResponseBody
	@PostMapping("/shoppingCart/delCartItem")
	public void delCartItem(@RequestParam("cartId") int cartId) {
		sCartService.delCartItem(cartId);
	}
	
	//取會員購物車
	@GetMapping("/shoppingCart/memberCart")
	public String getMemberCart(HttpSession session, Model model) {
//		Object memberNoObj = session.getAttribute("memberNo");
//		if (memberNoObj != null) {
//			int memberNo = (int)memberNoObj;
			List<ShoppingCart> memberCartList = sCartService.getMemberCart(6);
			List<CartDTO> cartDTOList = new ArrayList<>();
			Member member = mService.findByNo(6);
			for(ShoppingCart aCart : memberCartList) {
				Product product = pService.getProduct(aCart.getProductNo());
				CartDTO cartDTO = new CartDTO();
				cartDTO.setCartId(aCart.getCartId());
				cartDTO.setProductNo(aCart.getProductNo());
				cartDTO.setItemQuantity(aCart.getItemQuantity());
				
				cartDTO.setProductName(product.getProductName());
				cartDTO.setProductPrice(product.getProductPrice());
				cartDTO.setProductQuantity(product.getProductQuantity());
				cartDTO.setProductInfo(product.getProductInfo());
				
				cartDTOList.add(cartDTO);
			}
			
			model.addAttribute("cartDTOList",cartDTOList);
			model.addAttribute("member", member);
//		}
		
		  return "product/shoppingCart";
//		  return "取到";
	}
}
