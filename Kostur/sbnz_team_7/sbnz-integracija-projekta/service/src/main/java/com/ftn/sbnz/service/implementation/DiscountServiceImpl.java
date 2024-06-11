package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.dto.DiscountDTO;
import com.ftn.sbnz.model.models.Discount;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.repository.DiscountRepository;
import com.ftn.sbnz.service.DiscountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {


    private final DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public Discount save(Discount discount) {
        return discountRepository.save(discount);
    }


    @Override
    public List<DiscountDTO> getUserDiscount(User user) {
        List<Discount> discounts = discountRepository.findByUserId(user.getId());
        return discounts.stream()
                .map(discount -> new DiscountDTO(discount))
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountDTO> getUnusedUserDiscount(User user) {
        List<Discount> discounts = discountRepository.findByUserIdAndUsedFalse(user.getId());
        return discounts.stream()
                .map(discount -> new DiscountDTO(discount))
                .collect(Collectors.toList());
    }
}
