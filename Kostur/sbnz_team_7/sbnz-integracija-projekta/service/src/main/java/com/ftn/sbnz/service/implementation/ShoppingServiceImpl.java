package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.dto.shoppings.ShoppingResponseDTO;
import com.ftn.sbnz.dto.shoppings.ShoppingUserDTO;
import com.ftn.sbnz.exception.BadRequestException;
import com.ftn.sbnz.kafka.EventHandler;
import com.ftn.sbnz.model.models.Discount;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.model.models.products.Shopping;
import com.ftn.sbnz.repository.DiscountRepository;
import com.ftn.sbnz.repository.ProductRepository;
import com.ftn.sbnz.repository.ShoppingRepository;
import com.ftn.sbnz.service.ShoppingService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public ShoppingResponseDTO save(String productId, ObjectId userId, String discountId) {
        System.out.println("save method called");
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
        ShoppingResponseDTO shoppingEvent = eventHandler.processShoppingEvent(shopping);

        // Apply discount if present
        if (discount.isPresent()) {
            double discountValue = discount.get().getValue();
            shoppingEvent.getShopping().setValue(shoppingEvent.getShopping().getValue() * (1 - discountValue));
            discount.get().setUsed(true);
            discountRepository.save(discount.get());
        }

        shoppingRepository.save(shoppingEvent.getShopping());
        return shoppingEvent;
    }


    @Override
    public List<Shopping> findAll() {
        return shoppingRepository.findAll();
    }

    @Override
    public List<ShoppingUserDTO> findShoppingsByUser(ObjectId userId) {
        List<Shopping> userShoppings = shoppingRepository.findByUserId(userId);
        List<ShoppingUserDTO> response = new ArrayList<>();
        for (Shopping shopping : userShoppings) {
            Product product = productRepository.findById(shopping.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
            ShoppingUserDTO shoppingUserDTO = new ShoppingUserDTO(shopping, product);
            response.add(shoppingUserDTO);
        }
        Collections.sort(response, new Comparator<ShoppingUserDTO>() {
            @Override
            public int compare(ShoppingUserDTO f1, ShoppingUserDTO f2) {
                return f2.getDate().compareTo(f1.getDate());
            }
        });
        return response;
    }

    @Override
    public void deleteAll() {
        shoppingRepository.deleteAll();
    }
}
