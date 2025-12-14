package org.jsp.SupplyChainManagement.Repository;

import java.util.Optional;

import org.jsp.SupplyChainManagement.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
	@Query("Select c from Customer c where c.contact=?1")
	Customer findByContact(long contact);
	
	@Query("SELECT o.customer FROM Orders o WHERE o.id = :orderId")
	Optional<Customer> findCustomerByOrderId(@Param("orderId") int orderId);

}
