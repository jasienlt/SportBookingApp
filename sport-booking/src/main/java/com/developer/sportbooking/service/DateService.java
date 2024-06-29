package com.developer.sportbooking.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
@Service
public interface DateService {
    public LocalDate convertStringToLocalDate(String s);
}
