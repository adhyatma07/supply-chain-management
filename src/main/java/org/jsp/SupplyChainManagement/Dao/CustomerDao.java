package org.jsp.SupplyChainManagement.Dao;

import java.util.List;
import java.util.Optional;

import org.jsp.SupplyChainManagement.Entity.Customer;
import org.jsp.SupplyChainManagement.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepository customerRepository;
	
	  // 1. Save Customer
    public Customer saveCustomer(Customer customer) 
    {
        return customerRepository.save(customer);
    }

    // 2. Get Customer by ID
    public Optional<Customer> getCustomerById(Integer id) 
    {
        return customerRepository.findById(id);
    }

    // 3. Get All Customers
    public List<Customer> getAllCustomers() 
    {
        return customerRepository.findAll();
    }

    // 4. Update Customer
    public Customer updateCustomer(Customer customer) 
    {
        return customerRepository.save(customer);
    }

    // 5. Delete Customer
    public void deleteCustomer(Integer id) 
    {
        customerRepository.deleteById(id);
    }

    // 6. Get Customer by Phone
    public Customer getCustomerByContact(long contact) 
    {
        return customerRepository.findByContact(contact);
    }

    //7.Get customer by order (pending)
    public Optional<Customer> getCustomerByOrders(int orderId)
    {
    	return customerRepository.findCustomerByOrderId(orderId);
    }
    
    
    // 8. Pagination and Sorting
    public Page<Customer> getCustomerByPaginationAndSorting(Integer pageNumber, Integer pageSize, String field) 
    {
        return customerRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
    }
}
