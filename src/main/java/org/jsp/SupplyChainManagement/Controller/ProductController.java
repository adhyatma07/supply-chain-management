package org.jsp.SupplyChainManagement.Controller;

import java.util.List;

import org.jsp.SupplyChainManagement.Dto.ResponseStructure;
import org.jsp.SupplyChainManagement.Entity.Product;
import org.jsp.SupplyChainManagement.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Product")
public class ProductController 
{
	@Autowired
	private ProductService productService;
	
	//1.Add Product
	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product)
	{
		return productService.saveProduct(product);
	}
	
	//2.Get Product By Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Product>> getProductById(@PathVariable Integer id)
	{
		return productService.getProductById(id);
	}
	
	//3.get All Products
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts()
	{
		return productService.getAllProducts();
	}
	
	//4.update product
	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product)
	{
		return productService.updateproduct(product);
	}
	
	//5.delete Product
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@PathVariable Integer id)
	{
		return productService.deleteProduct(id);
	}
	
	//6.get product by supplier
	@GetMapping("/supplier/{id}")
	public ResponseEntity<ResponseStructure<List<Product>>> getProductBySupplier(@PathVariable Integer id)
	{
		return productService.getProductBySupplier(id);
	}
	
	//7.Get product by pagination and sorting
	@GetMapping("/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Product>>> getproductByPaginationAndSorting(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,@PathVariable String field)
	{
		return productService.getProductByPaginationAndSorting(pageNumber, pageSize, field);
	}
	
	//8.get products by stockQuantity
	@GetMapping("/products/{stockQuantity}")
	public ResponseEntity<ResponseStructure<List<Product>>> getProductsByStockQuantity(@PathVariable Integer stockQuantity)
	{
		return productService.getProductByStockQuantity(stockQuantity);
	}
}
