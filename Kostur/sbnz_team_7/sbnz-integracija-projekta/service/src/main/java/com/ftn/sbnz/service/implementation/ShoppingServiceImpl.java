package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.dto.shoppings.ShoppingUserDTO;
import com.ftn.sbnz.exception.BadRequestException;
import com.ftn.sbnz.kafka.EventHandler;
import com.ftn.sbnz.model.models.Discount;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.model.models.products.Shopping;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.repository.DiscountRepository;
import com.ftn.sbnz.repository.ProductRepository;
import com.ftn.sbnz.repository.ShoppingRepository;
import com.ftn.sbnz.service.ShoppingService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingServiceImpl implements ShoppingService {


    private final ShoppingRepository shoppingRepository;
    private final ProductRepository productRepository;
//    private final ShoppingEventProducer shoppingEventProducer;

    private final EventHandler eventHandler;
    private final DiscountRepository discountRepository;

    @Autowired
    public ShoppingServiceImpl(ShoppingRepository shoppingRepository, ProductRepository productRepository, EventHandler eventHandler, DiscountRepository discountRepository) {
        this.shoppingRepository = shoppingRepository;
        this.productRepository = productRepository;
        this.eventHandler = eventHandler;
        this.discountRepository = discountRepository;
    }


    @Override
    public Shopping save(String productId, ObjectId userId, String discountId) {
        ObjectId productObjectId;
        try {
            productObjectId = new ObjectId(productId);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid productId format");
        }

        Optional<Discount> discount = Optional.empty();
        if (discountId != null) {
            try {
                discount = discountRepository.findById(new ObjectId(discountId));
            } catch (IllegalArgumentException e) {
                throw new BadRequestException("Invalid discountId format");
            }
        }

        Shopping shopping = new Shopping(productObjectId, userId, new Date());

        // Process shopping event
        Shopping shoppingEvent = eventHandler.processShoppingEvent(shopping);

        // Apply discount if present
        if (discount.isPresent()) {
            double discountValue = discount.get().getValue();
            shoppingEvent.setValue(shoppingEvent.getValue() * (1 - discountValue));
            discount.get().setUsed(true);
            discountRepository.save(discount.get());
        }

        return shoppingRepository.save(shoppingEvent);
    }


    @Override
    public List<Shopping> findAll() {
        return shoppingRepository.findAll();
    }

    @Override
    public List<ShoppingUserDTO> findShoppingsByUser(ObjectId userId) {
        List<Shopping> userShoppings = shoppingRepository.findByUserId(userId);
        List<ShoppingUserDTO> response = new ArrayList<>();
        for (Shopping shopping: userShoppings){
            Product product = productRepository.findById(shopping.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
            ShoppingUserDTO shoppingUserDTO = new ShoppingUserDTO(shopping, product);
            response.add(shoppingUserDTO);
        }
        return response;
    }

    @Override
    public void deleteAll() {
        shoppingRepository.deleteAll();
    }
}
