package org.jsp.SupplyChainManagement.Service;

import java.util.List;
import java.util.Optional;

import org.jsp.SupplyChainManagement.Dao.ProductDao;
import org.jsp.SupplyChainManagement.Dao.SupplyChainDao;
import org.jsp.SupplyChainManagement.Dto.ResponseStructure;
import org.jsp.SupplyChainManagement.Entity.Product;
import org.jsp.SupplyChainManagement.Entity.Supplier;
import org.jsp.SupplyChainManagement.Exception.IdNotFoundException;
import org.jsp.SupplyChainManagement.Exception.NoRecordsFoundException;
import org.jsp.SupplyChainManagement.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SupplyChainService 
{
	@Autowired
	private SupplyChainDao supplyChainDao;
	
	@Autowired
	private ProductRepository productRepository;
	
	//1.Save Supplier
	public ResponseEntity<ResponseStructure<Supplier>> saveSupplier(Supplier supplier)
	{
		ResponseStructure<Supplier> response=new ResponseStructure<Supplier>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Supplier is Saved");
		response.setData(supplyChainDao.saveSupplier(supplier));
		return new ResponseEntity<ResponseStructure<Supplier>>(response,HttpStatus.CREATED);
	}
	
	//2.Get Supplier By Id
	public ResponseEntity<ResponseStructure<Supplier>> getSupplierById(Integer id)
	{
		Optional<Supplier> opt=supplyChainDao.getSuplierById(id);
		ResponseStructure<Supplier> response=new ResponseStructure<Supplier>();
		if(!opt.isEmpty())
		{
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Supplier Record FOund");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Supplier>>(response,HttpStatus.FOUND);
		}
		else
		{
			throw new IdNotFoundException("Id not Found in the Supplier"+opt.get());
		}
	}
	
	//3.getAllSuppliers
	public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSuppliers()
	{
		List<Supplier> suppliers=supplyChainDao.getAllSuppliers();
		ResponseStructure<List<Supplier>> response=new ResponseStructure<List<Supplier>>();
		if(!suppliers.isEmpty())
		{
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Suppliers retrieved");
			response.setData(suppliers);
			return new ResponseEntity<ResponseStructure<List<Supplier>>>(response,HttpStatus.OK);
		}
		else
		{
			throw new NoRecordsFoundException("NO Record Found in Supplier"+suppliers);
		}
	}
	
	
	//4.Update Supplier
	public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(Supplier supplier)
	{
		ResponseStructure<Supplier> response=new ResponseStructure<Supplier>();
		Optional<Supplier> opt = supplyChainDao.getSuplierById(supplier.getId());

        if (opt.isPresent()) {
            Supplier updatedSupplier = supplyChainDao.saveSupplier(supplier);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Record Updated");
            response.setData(updatedSupplier);
            return ResponseEntity.ok(response);
        } else {
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("Id Not Found of Supplier");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
	
	//5.Delete Supplier
	public ResponseEntity<ResponseStructure<Supplier>> deleteSupplier(Integer id)
	{
		Optional<Supplier> opt=supplyChainDao.getSuplierById(id);
		ResponseStructure<Supplier> response=new ResponseStructure<Supplier>();
		if(!opt.isEmpty())
		{
			supplyChainDao.deleteSupplier(id);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Record Deleted Successfully");
            response.setData(opt.get());
            return ResponseEntity.ok(response);
		}
		else
		{
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage("Record Not Found");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	
	//6.Get supplier by product
	public ResponseEntity<ResponseStructure<Supplier>> getSupplierByProduct(Integer id)
	{
		Optional<Product> opt=productRepository.findById(id);
		ResponseStructure<Supplier> response=new ResponseStructure<Supplier>();
		if(opt.isPresent())
		{
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Supplier Found");
			response.setData(opt.get().getSupplier());
			return new ResponseEntity<ResponseStructure<Supplier>>(response,HttpStatus.FOUND);
		}
		else
		{
			throw new IdNotFoundException("Supplier id is not found in the product"+id);
		}
	}
	
	
	//7.pagination and sorting
	public ResponseEntity<ResponseStructure<Page<Supplier>>> getBookByPaginationAndSorting(Integer pageNumber,Integer pageSize,String field)
	{
		Page<Supplier> pages=supplyChainDao.getSupplierByPaginationAndSorting(pageNumber, pageSize, field);
		ResponseStructure<Page<Supplier>> response=new ResponseStructure<Page<Supplier>>();
		if(!pages.isEmpty())
		{
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Supplier are retrieved and sorted");
			response.setData(pages);
			return new ResponseEntity<ResponseStructure<Page<Supplier>>>(response,HttpStatus.OK);
		}
		else
		{
			throw new NoRecordsFoundException("No record found with the page number "+pageNumber+"and records "+pageSize+"in the field"+field);
		}
	}
}
