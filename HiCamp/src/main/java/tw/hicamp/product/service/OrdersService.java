package tw.hicamp.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.hicamp.product.model.Orders;
import tw.hicamp.product.model.OrdersRepository;


@Service
public class OrdersService {

	@Autowired
	private OrdersRepository oRepo;

	// 新增一筆訂單
	public Orders addOrder(Orders order) {
		return oRepo.save(order);
	}

	// 查詢一筆訂單
	public Orders getOrder(Integer orderNo) {
		Optional<Orders> optional = oRepo.findById(orderNo);
		
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	// 查詢全部訂單
	public List<Orders> getAllOrders() {
		return oRepo.findAll();
	}

	// 修改訂單
	@Transactional
	public Orders updateOrder(Orders order) {
		Optional<Orders> optional = oRepo.findById(order.getOrderNo());
		
		if (optional.isPresent()) {
			Orders order1 = optional.get();
			return order1;
		}
		System.out.println("no update data");
		return null;
	}

	// 刪除訂單
	public void delOrder(Integer orderNo) {
		oRepo.deleteById(orderNo);
	}
	
//	public Orders findLastest() {
//		return oRepo.findFirstByOrderNoByOrderDateDesc();
//	}
	
	public Page<Orders> findByPage(Integer pageNumber) {
		Pageable pgb = PageRequest.of(pageNumber-1, 3, Sort.Direction.DESC, "added");
		
		Page<Orders> page = oRepo.findAll(pgb);
		
		return page;
	}

}