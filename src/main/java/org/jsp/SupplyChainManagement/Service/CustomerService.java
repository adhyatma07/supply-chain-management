package org.jsp.SupplyChainManagement.Service;

import java.util.List;
import java.util.Optional;

import org.jsp.SupplyChainManagement.Dao.CustomerDao;
import org.jsp.SupplyChainManagement.Dto.ResponseStructure;
import org.jsp.SupplyChainManagement.Entity.Customer;
import org.jsp.SupplyChainManagement.Exception.IdNotFoundException;
import org.jsp.SupplyChainManagement.Exception.NoRecordsFoundException;
import org.jsp.SupplyChainManagement.Repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDoa;
	
//	@Autowired
//	private OrdersRepository ordersRepository;
	
	// 1. Save Customer
    public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) 
    {
        ResponseStructure<Customer> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Customer Saved Successfully");
        response.setData(customerDoa.saveCustomer(customer));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2. Get Customer By Id
    public ResponseEntity<ResponseStructure<Customer>> getCustomerById(Integer id) 
    {
        Optional<Customer> opt = customerDoa.getCustomerById(id);
        ResponseStructure<Customer> response = new ResponseStructure<>();
        if (opt.isPresent()) {
            response.setStatusCode(HttpStatus.FOUND.value());
            response.setMessage("Customer Found");
            response.setData(opt.get());
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        } else {
            throw new IdNotFoundException("Customer with id " + id + " not found");
        }
    }

    // 3. Get All Customers
    public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomers() 
    {
        List<Customer> customers = customerDoa.getAllCustomers();
        ResponseStructure<List<Customer>> response = new ResponseStructure<>();
        if (!customers.isEmpty()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Customers Retrieved");
            response.setData(customers);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new NoRecordsFoundException("No customers found");
        }
    }

    // 4. Update Customer
    public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer) 
    {
        ResponseStructure<Customer> response = new ResponseStructure<>();
        Optional<Customer> opt = customerDoa.getCustomerById(customer.getId());

        if (opt.isPresent()) 
        {
            Customer updatedCustomer = customerDoa.updateCustomer(customer);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Customer Updated Successfully");
            response.setData(updatedCustomer);
            return ResponseEntity.ok(response);
        } else 
        {
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("Customer Id Not Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // 5. Delete Customer
    public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(Integer id) 
    {
        Optional<Customer> opt = customerDoa.getCustomerById(id);
        ResponseStructure<Customer> response = new ResponseStructure<>();
        if (opt.isPresent()) 
        {
        	customerDoa.deleteCustomer(id);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Customer Deleted Successfully");
            response.setData(opt.get());
            return ResponseEntity.ok(response);
        } 
        else 
        {
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("Customer Not Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // 6. Get Customer By Phone
    public ResponseEntity<ResponseStructure<Customer>> getCustomerByContact(long contact) 
    {
        Customer customer = customerDoa.getCustomerByContact(contact);
        ResponseStructure<Customer> response = new ResponseStructure<>();
        if (customer != null) 
        {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Customer Found with Contact");
            response.setData(customer);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } 
        else 
        {
            throw new NoRecordsFoundException("No Customer found with contact " + contact);
        }
    }

    //7.Get customer by order
    public ResponseEntity<ResponseStructure<Customer>> getCustomerByOrderId(int orderId) {
        Optional<Customer> customerOpt = customerDoa.getCustomerByOrders(orderId);

        ResponseStructure<Customer> response = new ResponseStructure<>();
        
        if (customerOpt.isPresent()) {
            // If customer is found, return successful response
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Customer found successfully");
            response.setData(customerOpt.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // If no customer is found for the given order ID, throw exception or return not found
            response.setStatusCode(HttpStatus.NOT_FOUND.value());
            response.setMessage("No customer found for the given order ID");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    
    // 8. PaginationAndSorting
    @Transactional
    public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSorting(Integer pageNumber, Integer pageSize, String field) 
    {
        Page<Customer> pages = customerDoa.getCustomerByPaginationAndSorting(pageNumber, pageSize, field);
        ResponseStructure<Page<Customer>> response = new ResponseStructure<>();
        if (!pages.isEmpty()) 
        {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Customers Retrieved with Pagination and Sorting");
            response.setData(pages);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } 
        else 
        {
            throw new NoRecordsFoundException("No records found for page " + pageNumber);
        }
    }
}
