package tw.hicamp.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.hicamp.product.model.ShoppingCart;
import tw.hicamp.product.model.ShoppingCartRepository;

@Service
public class ShoppingCartService {
	@Autowired
	private ShoppingCartRepository scRepo;
	
	//塞購物車
	public ShoppingCart addCart(ShoppingCart cart) {
		return scRepo.save(cart);
	}
	
	//取所有購物車
	public List<ShoppingCart> getCart() {
		return scRepo.findAll();
	}
	
	//刪除購物車item
	public void delCartItem(Integer cartId) {
		scRepo.deleteById(cartId);
	}
	
	//取會員購物車
	public List<ShoppingCart> getMemberCart(int memberNo){
		return scRepo.findcartIdBymemberNo(memberNo);
	}
}
