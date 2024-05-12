package com.ftn.sbnz.util;

import com.ftn.sbnz.dto.product.ProductDTO;
import com.ftn.sbnz.model.models.products.Product;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {

    public static Product productToEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setPrice(productDTO.getPrice());
        product.setInstruction(productDTO.getInstruction());
        product.setVegan(productDTO.isVegan());
        product.setSkinTypes(productDTO.getSkinTypes());
        product.setBenefits(productDTO.getBenefits());
        product.setIngredients(productDTO.getIngredients());
        product.setPath(productDTO.getPath());
        return product;
    }
}
