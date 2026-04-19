package edu.productmanagementsystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.productmanagementsystem.entity.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer>{

	 Product findByProductnameAndBrand(String productname, String brand);
	//Select b from Book b where b.title and b.author=:author
	
	@Query("Select p from Product p where p.price=:price")
	public List<Product> getProductsByPrice(double price);

	public List<Product> findByPrice(double price);
	
}







	
	
	
