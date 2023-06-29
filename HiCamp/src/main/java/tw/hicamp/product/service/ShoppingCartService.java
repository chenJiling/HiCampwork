package tw.hicamp.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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
	
	//用productNo找購物車
	public ShoppingCart findCartByProductNo(int productNo, int memberNo) {
		return scRepo.findByProductNo(productNo, memberNo);
	}
	
	//update 數量
	@Transactional
	public boolean updateItemQuantity(ShoppingCart shoppingCart, int itemQ) {
		shoppingCart.setItemQuantity(shoppingCart.getItemQuantity() + itemQ);
		return true;
	}
	
	//刪除cart by memberNo
	public boolean delcartByMemberNo(int memberNo) {
		return scRepo.delCartByMember(memberNo);
	}
	
}
