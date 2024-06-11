package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.dto.product.ProductDTO;
import com.ftn.sbnz.dto.product.ProductFeedbackDTO;
import com.ftn.sbnz.dto.product.ProductSearchDTO;
import com.ftn.sbnz.model.models.products.Category;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.repository.CategoryRepository;
import com.ftn.sbnz.repository.IngredientRepository;
import com.ftn.sbnz.repository.ProductRepository;
import com.ftn.sbnz.service.ProductService;
import com.ftn.sbnz.util.CategoryInfo;
import com.ftn.sbnz.util.ObjectMapper;
import org.bson.types.ObjectId;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    private final IngredientRepository ingredientRepository;

    private final CategoryRepository categoryRepository;

    private final KieContainer kieContainer;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, IngredientRepository ingredientRepository, CategoryRepository categoryRepository, KieContainer kieContainer) {
        this.productRepository = productRepository;
        this.ingredientRepository = ingredientRepository;
        this.categoryRepository = categoryRepository;
        this.kieContainer = kieContainer;
    }


    @Override
    public Product save(ProductDTO productDTO) {
        CategoryInfo categoryInfo = determineLowestCategoryId(productDTO.getProductSearchDTO());
        Product product = ObjectMapper.productToEntity(productDTO, ingredientRepository, categoryInfo);
        return productRepository.save(product);
    }

    private CategoryInfo determineLowestCategoryId(ProductSearchDTO productSearchDTO) {
        String typeCategory = productSearchDTO.getTypeCategory();
        String ageCategory = productSearchDTO.getAgeCategory();
        String usageCategory = productSearchDTO.getUsageCategory();

        ObjectId typeCategoryId = null;
        ObjectId ageCategoryId = null;
        ObjectId usageCategoryId = null;
        StringBuilder pathBuilder = new StringBuilder();

        // Find the type category ID
        if (typeCategory != null) {
            Category typeCategoryObj = categoryRepository.findByNameAndParentIsNull(typeCategory);
            typeCategoryId = typeCategoryObj != null ? typeCategoryObj.getId() : null;
            if (typeCategoryId != null) {
                pathBuilder.append(typeCategory);
            }
        }

        // Find the age category ID
        if (typeCategoryId != null && ageCategory != null) {
            Category ageCategoryObj = categoryRepository.findByNameAndParent(ageCategory, typeCategoryId);
            ageCategoryId = ageCategoryObj != null ? ageCategoryObj.getId() : null;
            if (ageCategoryId != null) {
                if (pathBuilder.length() > 0) {
                    pathBuilder.append("/");
                }
                pathBuilder.append(ageCategory);
            }
        }

        // Find the usage category ID
        if (ageCategoryId != null && usageCategory != null) {
            Category usageCategoryObj = categoryRepository.findByNameAndParent(usageCategory, ageCategoryId);
            usageCategoryId = usageCategoryObj != null ? usageCategoryObj.getId() : null;
            if (usageCategoryId != null) {
                if (pathBuilder.length() > 0) {
                    pathBuilder.append("/");
                }
                pathBuilder.append(usageCategory);
            }
        }

        // Return the lowest category ID and path
        ObjectId lowestCategoryId = usageCategoryId != null ? usageCategoryId
                : ageCategoryId != null ? ageCategoryId
                : typeCategoryId != null ? typeCategoryId
                : null;

        if (lowestCategoryId == null) {
            throw new IllegalArgumentException("Invalid category information provided.");
        }

        return new CategoryInfo(lowestCategoryId, pathBuilder.toString());
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductFeedbackDTO> getAllProducts() {
        List<ProductFeedbackDTO> productFeedbackDTOS = new ArrayList<>();
        for (Product product: productRepository.findAll()){
            ProductFeedbackDTO productFeedbackDTO = new ProductFeedbackDTO(product);
            productFeedbackDTOS.add(productFeedbackDTO);
        }
        return productFeedbackDTOS;
    }


}
