package com.ftn.sbnz.dto.shoppings;

import com.ftn.sbnz.dto.DiscountDTO;
import com.ftn.sbnz.dto.GiftDTO;
import com.ftn.sbnz.model.models.products.Shopping;

import java.util.List;

public class ShoppingResponseDTO {
    private Shopping shopping;
    private List<DiscountDTO> discounts;

    private GiftDTO giftDTO;

    public ShoppingResponseDTO(Shopping shopping, List<DiscountDTO> discounts) {
        this.shopping = shopping;
        this.discounts = discounts;
    }

    public GiftDTO getGiftDTO() {
        return giftDTO;
    }

    public void setGiftDTO(GiftDTO giftDTO) {
        this.giftDTO = giftDTO;
    }

    public ShoppingResponseDTO(Shopping shopping, List<DiscountDTO> discounts, GiftDTO giftDTO) {
        this.shopping = shopping;
        this.discounts = discounts;
        this.giftDTO = giftDTO;
    }

    // Getters and setters
    public Shopping getShopping() {
        return shopping;
    }

    public void setShopping(Shopping shopping) {
        this.shopping = shopping;
    }

    public List<DiscountDTO> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<DiscountDTO> discounts) {
        this.discounts = discounts;
    }
}
