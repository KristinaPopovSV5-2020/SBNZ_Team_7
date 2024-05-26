package com.ftn.sbnz.controller;

import com.ftn.sbnz.dto.product.FeedbackDTO;
import com.ftn.sbnz.exception.BadRequestException;
import com.ftn.sbnz.exception.NotFoundException;
import com.ftn.sbnz.model.models.Feedback;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api/feedback")
public class FeedbackController {


    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> addFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            throw new NotFoundException("User does not exist!");
        }
        Feedback feedback = feedbackService.save(feedbackDTO, user.getId());
        if (feedback == null) {
            throw new BadRequestException("You cannot rate the product!");
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
