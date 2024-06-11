package com.ftn.sbnz.service;

import com.ftn.sbnz.dto.BudgetDTO;
import com.ftn.sbnz.dto.product.RecommendedDTO;
import org.bson.types.ObjectId;

import java.util.List;


public interface RecommendationService {

    List<RecommendedDTO> recommendProductsBasedOnProblemsAndHabitsForUser(ObjectId objectId, List<String> skinProblems, List<String> lifestyleHabits);

    List<RecommendedDTO> recommendProductsForUser(ObjectId userId, BudgetDTO budgetDTO);

}
