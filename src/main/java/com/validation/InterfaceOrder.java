public interface OrderService {
    boolean createOrder(orderDto, UserDto userDto);
    boolean processOrder(Long orderId, OrderStatus status, UserDto userDto);
    boolean saveOrder(OrderDto orderDto);
    OrderDto getOrderStatus(Long orderId, UserDto userDto);
    boolean cancelOrder(Long orderId, UserDto userDto);
}

public interface EmailService {
    void sendConfirmationEmail(OrderDto orderDto, UserDto userDto);
    void sendOrderCancellationNotification(OrderDto orderDto, UserDto userDto);
}
