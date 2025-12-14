package org.jsp.SupplyChainManagement.Service;

import java.util.List;
import java.util.Optional;

import org.jsp.SupplyChainManagement.Dao.OrdersDao;
import org.jsp.SupplyChainManagement.Dto.ResponseStructure;
import org.jsp.SupplyChainManagement.Entity.Customer;
import org.jsp.SupplyChainManagement.Entity.Orders;
import org.jsp.SupplyChainManagement.Exception.IdNotFoundException;
import org.jsp.SupplyChainManagement.Exception.NoRecordsFoundException;
import org.jsp.SupplyChainManagement.Repository.CustomerRepository;
import org.jsp.SupplyChainManagement.Repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // 1. Place Order
    public ResponseEntity<ResponseStructure<Orders>> placeorders(Orders orders) 
    {
        ResponseStructure<Orders> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Order Placed");
        response.setData(ordersDao.placeorders(orders));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2. Get Order By Id
    public ResponseEntity<ResponseStructure<Orders>> getOrderById(Integer id) 
    {
        Optional<Orders> opt = ordersRepository.findById(id);
        ResponseStructure<Orders> response = new ResponseStructure<>();
        if (opt.isPresent()) 
        {
            response.setStatusCode(HttpStatus.FOUND.value());
            response.setMessage("Order Retrieved");
            response.setData(opt.get());
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        } else {
            throw new IdNotFoundException("Order Id Not Found: " + id);
        }
    }

    // 3. Get All Orders
    public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrders()
    {
        List<Orders> orders = ordersRepository.findAll();
        ResponseStructure<List<Orders>> response = new ResponseStructure<>();
        if (!orders.isEmpty()) 
        {
            response.setStatusCode(HttpStatus.FOUND.value());
            response.setMessage("Orders Retrieved");
            response.setData(orders);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        } 
        else 
        {
            throw new NoRecordsFoundException("No Orders Found");
        }
    }

    // 4. Update Order
    public ResponseEntity<ResponseStructure<Orders>> updateOrder(Orders orders) 
    {
        Optional<Orders> opt = ordersRepository.findById(orders.getId());
        ResponseStructure<Orders> response = new ResponseStructure<>();
        if (opt.isPresent()) 
        {
            orders.setId(opt.get().getId()); // keep same id
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Order Updated Successfully");
            response.setData(ordersRepository.save(orders));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } 
        else 
        {
            throw new IdNotFoundException("Order Id Not Found: " + orders.getId());
        }
    }

    // 5. Delete Order
    public ResponseEntity<ResponseStructure<Orders>> deleteOrder(Integer id) 
    {
        Optional<Orders> opt = ordersRepository.findById(id);
        ResponseStructure<Orders> response = new ResponseStructure<>();
        if (opt.isPresent()) 
        {
            ordersRepository.deleteById(id);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Order Deleted Successfully");
            response.setData(opt.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } 
        else 
        {
            throw new IdNotFoundException("Order Id Not Found: " + id);
        }
    }

    // 6. Get Order By Tracking Number
    public ResponseEntity<ResponseStructure<Orders>> getOrderByTrackingNumber(String trackingNumber) 
    {
        Optional<Orders> opt = ordersRepository.findByTrackingNumber(trackingNumber);
        ResponseStructure<Orders> response = new ResponseStructure<>();
        if (opt.isPresent()) 
        {
            response.setStatusCode(HttpStatus.FOUND.value());
            response.setMessage("Order Retrieved By Tracking Number");
            response.setData(opt.get());
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        } else {
            throw new IdNotFoundException("Tracking Number Not Found: " + trackingNumber);
        }
    }

    // 7. Get Orders With Amount Greater Than Value
    public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByAmountGreater(double amount) 
    {
        List<Orders> orders = ordersRepository.findByTotalAmountGreaterThan(amount);
        ResponseStructure<List<Orders>> response = new ResponseStructure<>();
        if (!orders.isEmpty()) 
        {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Orders Retrieved With Amount Greater Than " + amount);
            response.setData(orders);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } 
        else 
        {
            throw new NoRecordsFoundException("No Orders Found With Amount Greater Than: " + amount);
        }
    }

    // 8. Get Orders By Status
    public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByStatus(String status) 
    {
        List<Orders> orders = ordersRepository.findByStatus(status);
        ResponseStructure<List<Orders>> response = new ResponseStructure<>();
        if (!orders.isEmpty()) 
        {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Orders Retrieved By Status: " + status);
            response.setData(orders);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } 
        else 
        {
            throw new NoRecordsFoundException("No Orders Found With Status: " + status);
        }
    }

    // 9. Get Orders By Customer
    public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByCustomer(Integer customerId) 
    {
        Optional<Customer> opt = customerRepository.findById(customerId);
        if (opt.isPresent()) 
        {
            List<Orders> orders = ordersRepository.findByCustomer(opt.get());
            ResponseStructure<List<Orders>> response = new ResponseStructure<>();
            if (!orders.isEmpty()) 
            {
                response.setStatusCode(HttpStatus.OK.value());
                response.setMessage("Orders Retrieved For Customer Id: " + customerId);
                response.setData(orders);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } 
            else 
            {
                throw new NoRecordsFoundException("No Orders Found For Customer Id: " + customerId);
            }
        } 
        else 
        {
            throw new IdNotFoundException("Customer Not Found With Id: " + customerId);
        }
    }

    // 10. Get Orders By Pagination And Sorting
    public ResponseEntity<ResponseStructure<Page<Orders>>> getOrdersByPaginationAndSorting(int pageNumber, int pageSize, String field) 
    {
        Page<Orders> page = ordersRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field)));
        ResponseStructure<Page<Orders>> response = new ResponseStructure<>();
        if (!page.isEmpty()) 
        {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Orders Retrieved Successfully With Pagination and Sorting");
            response.setData(page);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } 
        else 
        {
            throw new NoRecordsFoundException("No Orders Found For Pagination and Sorting");
        }
    }
}
