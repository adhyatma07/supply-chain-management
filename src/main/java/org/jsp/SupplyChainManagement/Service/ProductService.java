package org.jsp.SupplyChainManagement.Service;

import java.util.List;
import java.util.Optional;

import org.jsp.SupplyChainManagement.Dao.ProductDao;
import org.jsp.SupplyChainManagement.Dto.ResponseStructure;
import org.jsp.SupplyChainManagement.Entity.Product;
import org.jsp.SupplyChainManagement.Entity.Supplier;
import org.jsp.SupplyChainManagement.Exception.IdNotFoundException;
import org.jsp.SupplyChainManagement.Exception.NoRecordsFoundException;
import org.jsp.SupplyChainManagement.Repository.SupplyChainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService 
{
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private SupplyChainRepository supplyChainRepository;
	
	//1.Add Product
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) 
	{
	    if (product.getSupplier() == null || product.getSupplier().getId() == 0) 
	    {
	        throw new IdNotFoundException("Supplier id missing!");
	    }

	    Optional<Supplier> opt = supplyChainRepository.findById(product.getSupplier().getId());
	    if (opt.isPresent()) 
	    {
	        product.setSupplier(opt.get());

	        ResponseStructure<Product> response = new ResponseStructure<>();
	        response.setStatusCode(HttpStatus.CREATED.value());
	        response.setMessage("Product saved Successfully!");
	        response.setData(productDao.saveProduct(product));
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    } 
	    else 
	    {
	        throw new IdNotFoundException("Supplier Id not found: " + product.getSupplier().getId());
	    }
	}
	
	//2.Get Product By Id
	public ResponseEntity<ResponseStructure<Product>> getProductById(Integer id)
	{
		Optional<Product> opt=productDao.getProductById(id);
		ResponseStructure<Product> response=new ResponseStructure<Product>();
		if(opt.isPresent())
		{
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Product Retrived");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Product>>(response,HttpStatus.FOUND);
		}
		else
		{
			throw new IdNotFoundException("Product Id Not found"+opt.get());
		}
	}
	
	//3.get All Products
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts()
	{
		List<Product> products=productDao.getAllProducts();
		ResponseStructure<List<Product>> response=new ResponseStructure<List<Product>>();
		if(!products.isEmpty())
		{
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Products Retrieved");
			response.setData(products);
			return new ResponseEntity<ResponseStructure<List<Product>>>(response,HttpStatus.FOUND);
		}
		else
		{
			throw new NoRecordsFoundException("NO Products found: "+products);
		}
	}
	
	//	4. Update product
	public ResponseEntity<ResponseStructure<Product>> updateproduct(Product product)
	{
		Optional<Product> opt=productDao.updateproduct(product);
		ResponseStructure<Product> response=new ResponseStructure<Product>();
		if(opt.isPresent())
		{
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Product Updated Successfully!");
			response.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(response,HttpStatus.FOUND);
		}
		else
		{
			throw new IdNotFoundException("Product Id"+product.getId()+" Not Found Product Can't Be Updated");
		}
	}
	
	//5. Delete product
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(Integer id)
	{
		Optional<Product> opt=productDao.getProductById(id);
		ResponseStructure<Product> response=new ResponseStructure<Product>();
		if(!opt.isEmpty())
		{
			productDao.deleteProduct(id);
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Product Deleted Successfully");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Product>>(response,HttpStatus.FOUND);
		}
		else
		{
			throw new IdNotFoundException("Product Id not found "+opt.get());
		}
	}
	
	//6.Get product by supplier
	public ResponseEntity<ResponseStructure<List<Product>>> getProductBySupplier(Integer id)
	{
		List<Product> products=productDao.getProductBySupplier(id);
		ResponseStructure<List<Product>> response=new ResponseStructure<List<Product>>();
		if(!products.isEmpty())
		{
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Product Retrieved Successfully");
			response.setData(products);
			return new ResponseEntity<ResponseStructure<List<Product>>>(response,HttpStatus.FOUND);
		}
		else
		{
			throw new IdNotFoundException("Supplier Not found for the product");
		}
	}
	
	//7.Get product by pagination and sorting
	public ResponseEntity<ResponseStructure<Page<Product>>> getProductByPaginationAndSorting(Integer pageNumber,Integer pageSize,String field)
	{
		Page p=productDao.getProductByPaginationAndSorting(pageNumber, pageSize, field);
		ResponseStructure<Page<Product>> response=new ResponseStructure<Page<Product>>();
		if(!p.isEmpty())
		{
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Product Retrieved Successfully");
			response.setData(p);
			return new ResponseEntity<ResponseStructure<Page<Product>>>(response,HttpStatus.OK);
		}
		else
		{
			throw new NoRecordsFoundException("No records Are Available");
		}
	}
	
	//8.get products by stockQuantity
	public ResponseEntity<ResponseStructure<List<Product>>> getProductByStockQuantity(Integer stockQuantity)
	{
		List<Product> products=productDao.getProductByStockQuantity(stockQuantity);
		ResponseStructure<List<Product>> response=new ResponseStructure<List<Product>>();
		if(!products.isEmpty())
		{
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("StockQuantity Retrieved");
			response.setData(products);
			return new ResponseEntity<ResponseStructure<List<Product>>>(response,HttpStatus.OK);
		}
		else
		{
			throw new NoRecordsFoundException("NO Products found: "+stockQuantity);
		}
	}
}
