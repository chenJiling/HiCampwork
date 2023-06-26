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
import tw.hicamp.product.model.Product;
import tw.hicamp.product.model.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository pRepo;

	public Product addProduct(Product product) {
		return pRepo.save(product);
	}

	public Product getProduct(Integer productNo) {
		Optional<Product> optinal = pRepo.findById(productNo);
		
		if (optinal.isPresent()) {
			return optinal.get();
		}
		return null;
		
	}

	public List<Product> getAllProduct() {
		return pRepo.findAll();
	}
	
	//修改
	@Transactional
	public Product updateProductByNo(Product product) {
		Optional<Product> optinal = pRepo.findById(product.getProductNo());
		
		if (optinal.isPresent()) {
			Product product1 = optinal.get();
			product1.setProductName(product.getProductName());
			product1.setProductPrice(product.getProductPrice());
			product1.setProductType(product.getProductType());
			product1.setProductQuantity(product.getProductQuantity());
			product1.setProductInfo(product.getProductInfo());
			product1.setProductStutas(product.getProductStutas());
			return product1;
		}
		System.out.println("no update data");
		return null;
	}
	
	//分頁功能
	public Page<Product> findByPage(Integer pageNumber) {
		Pageable pgb = PageRequest.of(pageNumber-1, 3, Sort.Direction.DESC, "added");
		
		Page<Product> page = pRepo.findAll(pgb);
		
		return page;
	}

}
