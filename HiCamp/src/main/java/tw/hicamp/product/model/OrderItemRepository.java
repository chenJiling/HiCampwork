package tw.hicamp.product.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	@Query(value = "select top (1) * from orders where orderNo= :orderNo order by orderItem DESC", nativeQuery = true)
	OrderItem findItemByOrderNo(@Param("orderNo") int orderNo);
}
