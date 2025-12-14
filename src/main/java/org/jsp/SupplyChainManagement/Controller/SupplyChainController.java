package org.jsp.SupplyChainManagement.Controller;

import java.util.List;

import org.jsp.SupplyChainManagement.Dto.ResponseStructure;
import org.jsp.SupplyChainManagement.Entity.Supplier;
import org.jsp.SupplyChainManagement.Service.SupplyChainService;
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

@RequestMapping("/api/supplier")
@RestController
public class SupplyChainController 
{
	@Autowired
	private SupplyChainService supplyChainService;
	
	//1.Save Supplier
	@PostMapping
	public ResponseEntity<ResponseStructure<Supplier>> saveSupplier(@RequestBody Supplier supplier)
	{
		return supplyChainService.saveSupplier(supplier);
	}
	
	//2.Get Supplier By Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> getSupplierById(@PathVariable Integer id)
	{
		return supplyChainService.getSupplierById(id);
	}
	
	//3.Get All Suppliers
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSuppliers()
	{
		return supplyChainService.getAllSuppliers();
	}
	
	
	//4.update Supplier
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(@PathVariable Integer id,@RequestBody Supplier supplier)
	{
		supplier.setId(id);
		return supplyChainService.updateSupplier(supplier);
	}
	
	
	//5.Delete Supplier
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> deleteSupplier(@PathVariable Integer id)
	{
		return supplyChainService.deleteSupplier(id);
	}
	
	
	
	//6.get Supplier by product
	@GetMapping("/product/{id}")
	public ResponseEntity<ResponseStructure<Supplier>> getSupplierByProduct(@PathVariable Integer id) {
	    return supplyChainService.getSupplierByProduct(id);
	}

	
	//7.pagination and sorting
	@GetMapping("/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Supplier>>> getBookByPaginationAndSorting(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,@PathVariable String field)
    {
    	return supplyChainService.getBookByPaginationAndSorting(pageNumber, pageSize, field);
    }
}
