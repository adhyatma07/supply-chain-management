package org.jsp.SupplyChainManagement.Controller;

import java.util.List;

import org.jsp.SupplyChainManagement.Dto.ResponseStructure;
import org.jsp.SupplyChainManagement.Entity.Customer;
import org.jsp.SupplyChainManagement.Service.CustomerService;
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
@RequestMapping("/api/customer")
public class CustomerController 
{
	@Autowired
	private CustomerService customerService;
	
	// 1. Save Customer
    @PostMapping
    public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer) 
    {
        return customerService.saveCustomer(customer);
    }

    // 2. Get Customer By Id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable Integer id) 
    {
        return customerService.getCustomerById(id);
    }

    // 3. Get All Customers
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomers() 
    {
        return customerService.getAllCustomers();
    }

    // 4. Update Customer
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@PathVariable Integer id, @RequestBody Customer customer)
    {
        customer.setId(id);
        return customerService.updateCustomer(customer);
    }

    // 5. Delete Customer
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@PathVariable Integer id) 
    {
        return customerService.deleteCustomer(id);
    }

    // 6. Get Customer By Phone
    @GetMapping("/contact/{contact}")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerByContact(@PathVariable Long contact) 
    {
        return customerService.getCustomerByContact(contact);
    }

    //7.Get customer by order
    @GetMapping("/{orderId}/customer")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerByOrderId(@PathVariable Integer orderId) {
        return customerService.getCustomerByOrderId(orderId);
    }
    
    // 8. PaginationAndSorting
    @GetMapping("/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSorting(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,@PathVariable String field) 
    {
        return customerService.getCustomerByPaginationAndSorting(pageNumber, pageSize, field);
    }

}
