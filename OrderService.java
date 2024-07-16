№1

public interface OrderService {
    boolean createOrder(orderDto, UserDto userDto);
    boolean processOrder(Long orderId, OrderStatus status, UserDto userDto);
    boolean saveOrder(OrderDto orderDto);
    OrderDto getOrderStatus(Long orderId, UserDto userDto);
    boolean cancelOrder(Long orderId, UserDto userDto);
}

№2

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.entity.Order;
import com.example.myapp.entity.Product;
import com.example.myapp.repository.OrderRepository;
import java.util.Date;


public class OrderServiceImpl implements OrderService {

public class OrderService {

    @Override
    public boolean createOrder(OrderDto orderDto, UserDto userDto) {

        Order order = new Order();
        order.setOrderId(generateOrderId());
        order.setOrderDate(new Date());
        order.setCustomerId(userDto.getUserId());
        order.setProductId(orderDto.getProductId());
        order.setQuantity(orderDto.getQuantity());
        order.setTotalAmount(calculateTotalAmount(orderDto.getProductId(), orderDto.getQuantity()));
        order.setOrderStatus(OrderStatus.PENDING);

        return saveOrder(order);
    }

    @Override
    public boolean processOrder(Long orderId, OrderStatus status, UserDto userDto) {
        if (!isUserAuthorized(userDto)) {
            return false;
        }

        Order order = getOrder(orderId);

        order.setOrderStatus(status);

        return saveOrder(order);
    }

    private boolean saveOrder(Order order) {
        return true;
    }

    @Override
    public OrderDto getOrderStatus(Long orderId, UserDto userDto) {
      

        Order order = getOrder(orderId);

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setProductId(order.getProductId());
        orderDto.setQuantity(order.getQuantity());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setOrderStatus(order.getOrderStatus());

        return orderDto;
    }

    @Override
    public boolean cancelOrder(Long orderId, UserDto userDto) {


        Order order = getOrder(orderId);

        order.setOrderStatus(OrderStatus.CANCELLED);

        return saveOrder(order);
    }

    private Long generateOrderId() {
        return System.currentTimeMillis();
    }

    private double calculateTotalAmount(Long productId, int quantity) {
        return 100.0; // Replace with the actual calculation
    }

    private Order getOrder(Long orderId) {
        return new Order();
    }
}
}
