package tw.hicamp.product.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	@Query(value = "select top (1) * from orders where memberNo= :memberNo order by orderDate DESC", nativeQuery = true)
	Orders findlastOrderByMember(@Param("memberNo") int memberNo);
	
}
