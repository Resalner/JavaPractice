№1 

public interface ProductService {
    boolean createProduct(ProductDto productDto, UserDto userDto);

    boolean updateProduct(Long productId, ProductDto productDto, UserDto userDto);

    ProductDto getProductDetails(Long productId, UserDto userDto);

    List<ProductDto> getAllProducts(UserDto userDto);

    boolean deleteProduct(Long productId, UserDto userDto);
}

№2 

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean createProduct(ProductDto productDto, UserDto userDto) {
        User user = userRepository.findById(userDto.getUserId());
        if (!user.hasPermission(Permission.CREATE_PRODUCT)) {
            return false;
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setImage(productDto.getImage());
        productRepository.save(product);

        return true;
    }

    @Override
    public boolean updateProduct(Long productId, ProductDto productDto, UserDto userDto) {
        User user = userRepository.findById(userDto.getUserId());
        if (!user.hasPermission(Permission.UPDATE_PRODUCT)) {
            return false;
        }

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setCategory(productDto.getCategory());
            product.setImage(productDto.getImage());
            productRepository.save(product);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ProductDto getProductDetails(Long productId, UserDto userDto) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getCategory(),
                    product.getImage()
            );
        } else {
            return null;
        }
    }

    @Override
    public List<ProductDto> getAllProducts(UserDto userDto) {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getCategory(),
                        product.getImage()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteProduct(Long productId, UserDto userDto) {
        User user = userRepository.findById(userDto.getUserId());
        if (!user.hasPermission(Permission.DELETE_PRODUCT)) {
            return false;
        }

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
            return true;
        } else {
            return false;
        }
    }
}
