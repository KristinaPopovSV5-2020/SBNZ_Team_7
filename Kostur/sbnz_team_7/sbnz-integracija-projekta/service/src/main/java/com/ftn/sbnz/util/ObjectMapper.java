package com.ftn.sbnz.util;

import com.ftn.sbnz.dto.product.ProductDTO;
import com.ftn.sbnz.model.models.Ingredient;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.repository.IngredientRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {

    public static Product productToEntity(ProductDTO productDTO, IngredientRepository ingredientRepository) {
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
        product.setPath(productDTO.getPath());
        return product;
    }
}
