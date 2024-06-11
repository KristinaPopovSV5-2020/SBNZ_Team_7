package com.ftn.sbnz.service;

import com.ftn.sbnz.dto.DiscountDTO;
import com.ftn.sbnz.model.models.Discount;
import com.ftn.sbnz.model.models.user.User;

import java.util.List;

public interface DiscountService {

    Discount save(Discount discount);

    List<DiscountDTO> getUserDiscount(User user);

    List<DiscountDTO> getUnusedUserDiscount(User user);
}
