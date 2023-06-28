package tw.hicamp.product.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

	@Query(value = "select * from shoppingCart where memberNo = :memberNo", nativeQuery = true)
	List<ShoppingCart> findcartIdBymemberNo(@Param("memberNo") int memberNo);
}
