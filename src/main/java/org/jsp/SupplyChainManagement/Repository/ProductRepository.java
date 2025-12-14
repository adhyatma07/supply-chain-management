package org.jsp.SupplyChainManagement.Repository;


import java.util.List;

import org.jsp.SupplyChainManagement.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
	//6.Get product by supplier
	@Query("select p from Product p where p.supplier.id=?1")
	List<Product> getProductsBySupplierId(int supplierId);
	
	//8.Get Product by stockQuantity
	List<Product> getProductByStockQuantity(Integer stockQuantity);
}
