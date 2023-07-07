package tw.hicamp.product.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	@Query(value = "select top (1) * from orders where orderNo= :orderNo order by orderItem DESC", nativeQuery = true)
	OrderItem findItemByOrderNo(@Param("orderNo") int orderNo);
	
	//類別分析
	@Query(value = "SELECT product.productType, SUM(shoppingCart.itemQuantity) AS totalQuantity FROM product JOIN shoppingCart ON product.productNo = shoppingCart.productNo GROUP BY product.productType;", nativeQuery = true)
	List<Map<String, Integer>> findtotalQuantityGroupByType();
	
//	SELECT product.productType, SUM(orderItem.itemQuantity) AS totalQuantity,SUM(orderItem.unitPrice) AS totalPrice FROM product JOIN orderItem ON product.productNo = orderItem.productNo GROUP BY product.productType;
}
