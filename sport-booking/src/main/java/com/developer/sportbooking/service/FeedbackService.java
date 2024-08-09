package com.developer.sportbooking.service;

import com.developer.sportbooking.entity.Feedback;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedbackService {
    List<Feedback> getAllFeedback();

    List<Feedback> getFeedbackByCourt(Long courtId);
}
