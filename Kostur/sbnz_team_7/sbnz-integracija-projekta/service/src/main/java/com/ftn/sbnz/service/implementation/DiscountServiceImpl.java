package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.model.models.Discount;
import com.ftn.sbnz.repository.DiscountRepository;
import com.ftn.sbnz.service.DiscountService;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {


    private final DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public Discount save(Discount discount) {
        return discountRepository.save(discount);
    }

}
