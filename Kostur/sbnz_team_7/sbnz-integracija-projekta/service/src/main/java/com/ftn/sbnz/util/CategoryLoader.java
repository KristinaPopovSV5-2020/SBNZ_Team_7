package com.ftn.sbnz.util;


import com.ftn.sbnz.model.models.products.Category;
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
            ObjectId creamsId = addCategoryIfNotExists("Creams", null);
            ObjectId lotionsId = addCategoryIfNotExists("Lotions", null);
            ObjectId serumsId = addCategoryIfNotExists("Serums", null);

            // Dodavanje podkategorija za svaku glavnu kategoriju
            addAgeAndUsageCategories(creamsId, "Creams");
            addAgeAndUsageCategories(lotionsId, "Lotions");
            addAgeAndUsageCategories(serumsId, "Serums");

            System.out.println("Added all categories and subcategories");
        }
    }

    private ObjectId addCategoryIfNotExists(String name, ObjectId parent) {
        Category category = new Category(new ObjectId(), name, parent);
        return categoryService.save(category).getId();
    }

    private void addAgeAndUsageCategories(ObjectId parentId, String parentName) {
        String[] ageGroups = {"Kids", "Youngsters", "Elderly"};
        String[] usageTimes = {"day", "night", "day&night"};

        for (String ageGroup : ageGroups) {
            ObjectId ageGroupId = addCategoryIfNotExists(ageGroup, parentId);
            for (String usageTime : usageTimes) {
                addCategoryIfNotExists(usageTime, ageGroupId);
            }
        }
    }
}