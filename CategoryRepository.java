public interface CategoryRepository {
    Category createCategory(CategoryDto categoryDto);
    Category getCategory(Long categoryId);
    List<Category> getAllCategories();
    Category updateCategory(Long categoryId, CategoryDto categoryDto);
    boolean deleteCategory(Long categoryId);
}

@Service
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryDao categoryDao;

    public CategoryRepositoryImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Category createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setCreatedAt(new Date());
        return categoryDao.save(category);
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryDao.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = getCategory(categoryId);
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setUpdatedAt(new Date());
        return categoryDao.save(category);
    }

    @Override
    public boolean deleteCategory(Long categoryId) {
        Category category = getCategory(categoryId);
        categoryDao.delete(category);
        return true;
    }
}

