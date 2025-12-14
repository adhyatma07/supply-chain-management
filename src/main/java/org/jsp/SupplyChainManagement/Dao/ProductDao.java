package org.jsp.SupplyChainManagement.Dao;

import java.util.List;
import java.util.Optional;

import org.jsp.SupplyChainManagement.Entity.Product;
import org.jsp.SupplyChainManagement.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao 
{
	@Autowired
	private ProductRepository productRepository;
	
	//1.Add Product
	public Product saveProduct(Product product)
	{
		return productRepository.save(product);
	}
	
	//2.Get Product By Id
	public Optional<Product> getProductById(Integer id)
	{
		return productRepository.findById(id);
	}
	
	//3.Get All Products
	public List<Product> getAllProducts()
	{
		return productRepository.findAll();
	}
	
	//4.update product
	public  Optional<Product> updateproduct(Product product)
	{
		Optional<Product> products= productRepository.findById(product.getId());
		if(products.isPresent())
		{
			Product setproduct=products.get();
			setproduct.setName(product.getName());
			setproduct.setPrice(product.getPrice());
			setproduct.setStockQuantity(product.getStockQuantity());
			setproduct.setSupplier(product.getSupplier());
			productRepository.save(setproduct);
			return Optional.of(setproduct);
		}
		else
		{
			return Optional.empty();
		}
	}
	
	
	//5. Delete product
	public void deleteProduct(Integer id)
	{
		productRepository.deleteById(id);
	}
	
	//6.Get product by supplier
	public List<Product> getProductBySupplier(Integer id)
	{
		return productRepository.getProductsBySupplierId(id);
	}
	
	//7.Get product by pagination and sorting
	public Page<Product> getProductByPaginationAndSorting(Integer pageNumber, Integer pageSize, String field) 
	{
	    return productRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
	}
	
	//8.get product by stockQuantity
	public List<Product> getProductByStockQuantity(Integer stockQuantity)
	{
		return productRepository.getProductByStockQuantity(stockQuantity);
	}
}
