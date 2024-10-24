package com.umpisa.customer.booking.service.impl;

import com.umpisa.customer.booking.service.SMSService;
import org.springframework.stereotype.Service;

@Service
public class SMSServiceImpl implements SMSService {

    @Override
    public void sendMessage(String message) {
        System.out.println("Message: " + message);
        System.out.println("Sent SMS");

    }
}
