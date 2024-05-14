package com.ftn.sbnz.util;


import com.ftn.sbnz.dto.product.ProductCategoryDTO;
import com.ftn.sbnz.model.models.products.ProductCategory;
import com.ftn.sbnz.service.CategoryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class CategoryLoader implements CommandLineRunner {

    @Value("${script.enabled}")
    private boolean scriptEnabled;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {
        if (scriptEnabled) {
            // Dodavanje glavnih kategorija
            ObjectId creamsId = addCategoryIfNotExists(new ProductCategoryDTO("Creams", 1, null));
            ObjectId lotionsId = addCategoryIfNotExists(new ProductCategoryDTO("Lotions", 1, null));
            ObjectId serumsId = addCategoryIfNotExists(new ProductCategoryDTO("Serums", 1, null));

            // Dodavanje podkategorija za svaku glavnu kategoriju
            addAgeAndUsageCategories(creamsId, "Creams");
            addAgeAndUsageCategories(lotionsId, "Lotions");
            addAgeAndUsageCategories(serumsId, "Serums");

            System.out.println("Added all categories and subcategories");
        }
    }

    private ObjectId addCategoryIfNotExists(ProductCategoryDTO dto) {
        ProductCategory savedCategory = categoryService.save(dto);
        return savedCategory.getId();
    }

    private void addAgeAndUsageCategories(ObjectId parentId, String parentName) {
        String[] ageGroups = {"Kids", "Youngsters", "Elderly"};
        String[] usageTimes = {"day", "night", "day&night"};

        for (String ageGroup : ageGroups) {
            ObjectId ageGroupId = addCategoryIfNotExists(new ProductCategoryDTO(ageGroup, 2, parentId));
            for (String usageTime : usageTimes) {
                addCategoryIfNotExists(new ProductCategoryDTO(usageTime, 3, ageGroupId));
            }
        }
    }
}

