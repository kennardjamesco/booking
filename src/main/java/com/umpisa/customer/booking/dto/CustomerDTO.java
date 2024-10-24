package com.umpisa.customer.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CustomerDTO {

    @JsonProperty("customer_id")
    private Integer customerId;
    private String name;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;
}
