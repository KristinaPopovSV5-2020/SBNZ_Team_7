package com.ftn.sbnz.service;

import com.ftn.sbnz.dto.product.ProductDTO;
import com.ftn.sbnz.dto.product.ProductSearchDTO;
import com.ftn.sbnz.model.models.products.Category;
import org.bson.types.ObjectId;

import java.util.List;


public interface CategoryService {

    Category save(Category category);

    List<Category> findAll();

    Category findById(ObjectId id);

    void deleteById(ObjectId id);

    List<ProductDTO> searchBackward(ProductSearchDTO productSearchDTO);

    void deleteAll();
}
