package tw.hicamp.product.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	@Query(value = "select * from orders where memberNo = :memberNo and orderDate = (select (MAX)orderDate from orders)", nativeQuery = true)
	Orders findMaxOrderByMember(@Param("memberNo") int memberNo);
	
}
