import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.entity.Order;
import com.example.myapp.entity.Product;
import com.example.myapp.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(List<Product> products) {
        Order order = new Order();
        order.setProducts(products);
        order.setTotalPrice(calculateTotalPrice(products));
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, List<Product> products) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + id));
        order.setProducts(products);
        order.setTotalPrice(calculateTotalPrice(products));
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + id));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    private double calculateTotalPrice(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public class ProductDetails {
        private Product product;
        private int quantity;
        private Category category;
    
        public ProductDetails(Product product, int quantity, Category category) {
            this.product = product;
            this.quantity = quantity;
            this.category = category;
        }
    
    }
}