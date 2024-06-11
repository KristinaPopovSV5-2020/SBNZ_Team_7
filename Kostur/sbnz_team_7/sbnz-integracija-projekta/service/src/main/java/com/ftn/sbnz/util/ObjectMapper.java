package com.ftn.sbnz.util;

import com.ftn.sbnz.dto.product.FeedbackDTO;
import com.ftn.sbnz.dto.product.ProductDTO;
import com.ftn.sbnz.model.models.Feedback;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.repository.IngredientRepository;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {

    public static Product productToEntity(ProductDTO productDTO, IngredientRepository ingredientRepository, CategoryInfo categoryInfo) {
        if (productDTO == null) {
            return null;
        }
        Product product = new Product();
        product.setPrice(productDTO.getPrice());
        product.setInstruction(productDTO.getInstruction());
        product.setVegan(productDTO.isVegan());
        product.setSkinTypes(productDTO.getSkinTypes());
        product.setBenefits(productDTO.getBenefits());
        if (productDTO.getIngredientIds() != null) {
            System.out.println(productDTO.getIngredientIds());
            List<ObjectId> ingredientIds = productDTO.getIngredientIds().stream()
                    .map(ObjectId::new)
                    .filter(id -> ingredientRepository.existsById(id))
                    .collect(Collectors.toList());
            System.out.println(ingredientIds);
            product.setIngredientIds(ingredientIds);
        }
        product.setName(productDTO.getName());
        product.setPath(productDTO.getPath());
        product.setCategoryId(categoryInfo.getLowestCategoryId());
        product.setPath(categoryInfo.getPath());
        return product;
    }

    public static Feedback feedbackToEntity(FeedbackDTO feedbackDTO, ObjectId userId) {
        if (feedbackDTO == null) {
            return null;
        }
        Feedback feedback = new Feedback();
        feedback.setProductId(new ObjectId(feedbackDTO.getProductId()));
        feedback.setUserId(userId);
        feedback.setRating(feedbackDTO.getRating());
        feedback.setDateTime(new Date());
        feedback.setNew(true);
        return feedback;
    }
}
