package org.jsp.SupplyChainManagement.Repository;

import java.util.List;
import java.util.Optional;

import org.jsp.SupplyChainManagement.Entity.Customer;
import org.jsp.SupplyChainManagement.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	Optional<Orders> findByTrackingNumber(String trackingNumber);
    List<Orders> findByTotalAmountGreaterThan(double amount);
    List<Orders> findByStatus(String status);
    List<Orders> findByCustomer(Customer customer);
}
