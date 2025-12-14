package org.jsp.SupplyChainManagement.Dao;

import java.util.List;
import java.util.Optional;

import org.jsp.SupplyChainManagement.Entity.Product;
import org.jsp.SupplyChainManagement.Entity.Supplier;
import org.jsp.SupplyChainManagement.Repository.SupplyChainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class SupplyChainDao 
{
	@Autowired
	private SupplyChainRepository supplyChainRepository;
	
	//1.Save Supplier
	public Supplier saveSupplier(Supplier supplier)
	{
		return supplyChainRepository.save(supplier);
	}
	
	
	//2.getSupplierByID
	public Optional<Supplier> getSuplierById(Integer id)
	{
		return supplyChainRepository.findById(id);
	}
	
	//3.getAllSuppliers
	public List<Supplier> getAllSuppliers()
	{
		return supplyChainRepository.findAll();
	}
	
	
	//4.Update Supplier
	public Supplier updateSupplier(Supplier supplier)
	{
		return supplyChainRepository.save(supplier);
	}
	
	//5.Delete Supplier
	public void deleteSupplier(Integer id)
	{
		supplyChainRepository.deleteById(id);
	}
	
	//6.Get supplier by product
	public Optional<Supplier> getSupplierByProduct(Product product)
	{
		return supplyChainRepository.findById(product.getSupplier().getId());
	}
	
	//7.pagination and sorting
	public Page<Supplier> getSupplierByPaginationAndSorting(Integer pageNumber,Integer pageSize,String field)
	{
		return supplyChainRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by(field).ascending()));
	}
}
