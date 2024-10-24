package com.umpisa.customer.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.umpisa.customer.booking.entity.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDTO {

    @JsonProperty("customer_id")
    private Integer customerId;
    private String name;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;
    @JsonProperty("reservation_id")
    private Integer reservationId;
    @JsonProperty("reservation_date")
    private LocalDateTime reservationDate;
    @JsonProperty("number_of_guest")
    private int numberOfGuest;
    @JsonProperty("reservation_status")
    private String status;
    @JsonProperty("notification_type")
    private NotificationType notificationType;

}
