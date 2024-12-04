import java.util.ArrayList;
import java.util.List;

public interface ProductRepository {
    void createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> getProductsByCategory(String category);
    void updateProduct(Long id, Product updatedProduct);
    void deleteProduct(Long id);
}

class Product {
    private Long id;
    private String name;
    private String category;
}

class ProductRepositoryImpl implements ProductRepository {
    private List<Product> products = new ArrayList<>();

    @Override
    public void createProduct(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    @Override
    public void updateProduct(Long id, Product updatedProduct) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                product.setName(updatedProduct.getName());
                product.setCategory(updatedProduct.getCategory());
                break;
            }
        }
    }

    @Override
    public void deleteProduct(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
