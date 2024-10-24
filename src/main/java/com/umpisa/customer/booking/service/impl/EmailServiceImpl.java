package com.umpisa.customer.booking.service.impl;

import com.umpisa.customer.booking.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Message: " + message);
        System.out.println("Sent Email");
    }
}
