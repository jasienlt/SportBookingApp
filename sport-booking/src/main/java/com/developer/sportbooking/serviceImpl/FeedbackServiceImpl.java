package com.developer.sportbooking.serviceImpl;

import com.developer.sportbooking.entity.Feedback;
import com.developer.sportbooking.persistence.repository.FeedbackRepo;
import com.developer.sportbooking.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FeedbackServiceImp implements FeedbackService {
    @Autowired
    private FeedbackRepo feedbackRepo;

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepo.findAll();
    }

    @Override
    public List<Feedback> getFeedbackByCourt(Long courtId) {
        return feedbackRepo.findByCourtId(courtId);
    }
}
