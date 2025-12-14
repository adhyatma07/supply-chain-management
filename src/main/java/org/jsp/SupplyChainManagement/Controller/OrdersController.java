package org.jsp.SupplyChainManagement.Controller;

import org.jsp.SupplyChainManagement.Dto.ResponseStructure;
import org.jsp.SupplyChainManagement.Entity.Orders;
import org.jsp.SupplyChainManagement.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController 
{

    @Autowired
    private OrdersService ordersService;

    // 1. Place Order
    @PostMapping
    public ResponseEntity<ResponseStructure<Orders>> placeOrder(@RequestBody Orders orders) 
    {
        return ordersService.placeorders(orders);
    }

    // 2. Get Order By Id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Orders>> getOrderById(@PathVariable Integer id) 
    {
        return ordersService.getOrderById(id);
    }

    // 3. Get All Orders
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrders() 
    {
        return ordersService.getAllOrders();
    }

    // 4. Update Order
    @PutMapping
    public ResponseEntity<ResponseStructure<Orders>> updateOrder(@RequestBody Orders orders) 
    {
        return ordersService.updateOrder(orders);
    }

    // 5. Delete Order
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Orders>> deleteOrder(@PathVariable Integer id) 
    {
        return ordersService.deleteOrder(id);
    }

    // 6. Get Order By Tracking Number
    @GetMapping("/tracking/{trackingNumber}")
    public ResponseEntity<ResponseStructure<Orders>> getOrderByTrackingNumber(@PathVariable String trackingNumber)
    {
        return ordersService.getOrderByTrackingNumber(trackingNumber);
    }

    // 7. Get Orders With Amount Greater Than a Particular Value
    @GetMapping("/amount/{amount}")
    public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByAmountGreater(@PathVariable double amount) 
    {
        return ordersService.getOrdersByAmountGreater(amount);
    }

    // 8. Get Orders By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByStatus(@PathVariable String status) 
    {
        return ordersService.getOrdersByStatus(status);
    }

    // 9. Get Orders By Customer
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByCustomer(@PathVariable Integer customerId)
    {
        return ordersService.getOrdersByCustomer(customerId);
    }

    // 10. Get Orders By Pagination and Sorting
    @GetMapping("/pagination")
    public ResponseEntity<ResponseStructure<Page<Orders>>> getOrdersByPaginationAndSorting(
            @RequestParam int pageNumber, 
            @RequestParam int pageSize, 
            @RequestParam String field) 
    {
        return ordersService.getOrdersByPaginationAndSorting(pageNumber, pageSize, field);
    }
}
