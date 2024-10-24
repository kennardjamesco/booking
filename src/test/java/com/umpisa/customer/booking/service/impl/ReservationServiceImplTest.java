package com.umpisa.customer.booking.service.impl;


import com.umpisa.customer.booking.dto.CustomerDTO;
import com.umpisa.customer.booking.dto.ReservationDTO;
import com.umpisa.customer.booking.entity.CustomerEntity;
import com.umpisa.customer.booking.entity.NotificationType;
import com.umpisa.customer.booking.entity.ReservationEntity;
import com.umpisa.customer.booking.repository.CustomerRepository;
import com.umpisa.customer.booking.repository.ReservationRepository;
import com.umpisa.customer.booking.service.CustomerService;
import com.umpisa.customer.booking.service.EmailService;
import com.umpisa.customer.booking.service.ReservationService;
import com.umpisa.customer.booking.service.SMSService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReservationServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CustomerService customerService;

    @Mock
    private SMSService smsService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createReservation_Success() {

        ReservationDTO reservationDTO = ReservationDTO.builder()
                .name("John Smith")
                .phoneNumber("0917-500-1233")
                .email("john@gmail.com")
                .reservationDate(LocalDateTime.now().plusDays(1))
                .numberOfGuest(2)
                .notificationType(NotificationType.SMS)
                .build();

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(1);
        customerEntity.setName("John Smith");
        customerEntity.setPhoneNumber("0917-500-1233");
        customerEntity.setEmail("john@gmail.com");

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setReservationId(1);
        reservationEntity.setCustomer(customerEntity);
        reservationEntity.setReservationDate(reservationDTO.getReservationDate());
        reservationEntity.setNumberOfGuest(reservationDTO.getNumberOfGuest());
        reservationEntity.setStatus("CONFIRMED");
        when(customerRepository.findByPhoneNumberOptional("0917-500-1233")).thenReturn(Optional.empty());
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);
        when(reservationRepository.save(any(ReservationEntity.class))).thenReturn(reservationEntity);

        ReservationDTO result = reservationService.createReservation(reservationDTO);

        Assertions.assertThat(result).isNotNull();
        assertEquals("John Smith", result.getName());
        assertEquals("CONFIRMED", result.getStatus());

    }

    @Test
    void cancelReservation_Success() {

        ReservationDTO reservationDTO = ReservationDTO.builder()
                .name("John Smith")
                .phoneNumber("0917-500-1233")
                .email("john@gmail.com")
                .reservationDate(LocalDateTime.now().plusDays(1))
                .numberOfGuest(2)
                .notificationType(NotificationType.EMAIL)
                .build();

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(1);
        customerEntity.setName("John Smith");
        customerEntity.setPhoneNumber("0917-500-1233");
        customerEntity.setEmail("john@gmail.com");

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setReservationId(1);
        reservationEntity.setCustomer(customerEntity);
        reservationEntity.setReservationDate(reservationDTO.getReservationDate());
        reservationEntity.setNumberOfGuest(reservationDTO.getNumberOfGuest());
        reservationEntity.setStatus("CONFIRMED");

        when(reservationRepository.findById(1)).thenReturn(Optional.of(reservationEntity));
        when(reservationRepository.save(any(ReservationEntity.class))).thenReturn(reservationEntity);

        ReservationDTO result = reservationService.cancelReservation(1);

        Assertions.assertThat(result).isNotNull();
        assertEquals("CANCELLED", result.getStatus());

    }

    @Test
    void updateReservation_Success() {

        ReservationDTO reservationDTO = ReservationDTO.builder()
                .name("John Smith")
                .phoneNumber("0917-500-1233")
                .email("john@gmail.com")
                .reservationDate(LocalDateTime.now().plusDays(1))
                .numberOfGuest(2)
                .notificationType(NotificationType.SMS)
                .build();

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(1);
        customerEntity.setName("John Smith");
        customerEntity.setPhoneNumber("0917-500-1233");
        customerEntity.setEmail("john@gmail.com");

        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setReservationId(1);
        reservationEntity.setCustomer(customerEntity);
        reservationEntity.setReservationDate(reservationDTO.getReservationDate());
        reservationEntity.setNumberOfGuest(reservationDTO.getNumberOfGuest());
        reservationEntity.setStatus("CONFIRMED");

        ReservationDTO updatedReservationDTO = ReservationDTO.builder()
                .reservationDate(LocalDateTime.now().plusDays(1))
                .numberOfGuest(10)
                .build();

        when(reservationRepository.findByReservationId(1)).thenReturn(Optional.of(reservationEntity));
        when(reservationRepository.save(any(ReservationEntity.class))).thenReturn(reservationEntity);

        // Call the update method
        ReservationDTO result = reservationService.updateReservation(1, updatedReservationDTO);

        // Assertions
        Assertions.assertThat(result).isNotNull(); // Ensure result is not null
        assertEquals(10, result.getNumberOfGuest());


    }




}
