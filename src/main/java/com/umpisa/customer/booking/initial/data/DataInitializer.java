package com.umpisa.customer.booking.initial.data;


import com.umpisa.customer.booking.entity.NotificationType;
import com.umpisa.customer.booking.repository.CustomerRepository;
import com.umpisa.customer.booking.repository.ReservationRepository;
import com.umpisa.customer.booking.dto.ReservationDTO;
import com.umpisa.customer.booking.service.CustomerService;
import com.umpisa.customer.booking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner {

    private final ReservationService reservationService;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public void run(String... args) throws Exception {
//        customerService.saveCustomer(new CustomerDTO(1,"Customer 1","0917-104-1111","customer_1@gmail.com"));
//        customerService.saveCustomer(new CustomerDTO(2,"Customer 2","0917-104-1112","customer_2@gmail.com"));
//        customerService.saveCustomer(new CustomerDTO(3,"Customer 3","0917-104-1113","customer_3@gmail.com"));
//        customerService.saveCustomer(new CustomerDTO(4,"Customer 4","0917-104-1114","customer_4@gmail.com"));
//        customerService.saveCustomer(new CustomerDTO(5,"Customer 5","0917-104-1115","customer_5@gmail.com"));
//        customerService.saveCustomer(new CustomerDTO(6,"Customer 6","0917-104-1116","customer_6@gmail.com"));
//        customerRepository.save(new com.umpisa.customer.booking.Entity.CustomerEntity(null,"Customer 1","0917-104-1111","customer_1@gmail.com"));
//        CustomerEntity customer1 = customerRepository.save(CustomerEntity.builder()
//                .customerId(null) // Optional, can be omitted for auto-generated IDs
//                .name("Customer 1")
//                .phoneNumber("0917-104-1111")
//                .email("customer_1@gmail.com")
//                .build());
//
//
//        CustomerEntity customer2 = customerRepository.save(CustomerEntity.builder()
//                .name("Customer 2")
//                .phoneNumber("0917-104-1112")
//                .email("customer_2@gmail.com")
//                .build());
//
//
//        ReservationEntity reservation1 = ReservationEntity.builder()
//                .reservationDate(LocalDateTime.now().plusDays(1))
//                .numberOfGuest(4)
//                .status("CONFIRMED")
//                .customer(customer1)
//                .build();
//
//        ReservationEntity reservation2 = ReservationEntity.builder()
//                .reservationDate(LocalDateTime.now().plusDays(2))
//                .numberOfGuest(2)
//                .status("CANCELLED")
//                .customer(customer2)
//                .build();



        // Save reservations
//        reservationRepository.saveAll(Arrays.asList(reservation1, reservation2));


        // Clear previous data if necessary
        customerRepository.deleteAll();
        reservationRepository.deleteAll();


        ReservationDTO reservation1 = new ReservationDTO();
        reservation1.setName("Customer 1");
        reservation1.setPhoneNumber("0917-104-1111");
        reservation1.setEmail("customer_1@gmail.com");
        reservation1.setReservationDate(LocalDateTime.now().plusDays(1));
        reservation1.setNumberOfGuest(4);
        reservation1.setNotificationType(NotificationType.SMS);
        reservationService.createReservation(reservation1);

        ReservationDTO reservation2 = new ReservationDTO();
        reservation2.setName("Customer 2");
        reservation2.setPhoneNumber("0917-104-1112");
        reservation2.setEmail("customer_2@gmail.com");
        reservation2.setReservationDate(LocalDateTime.now().plusDays(2));
        reservation2.setNumberOfGuest(2);
        reservation2.setNotificationType(NotificationType.EMAIL);
        reservationService.createReservation(reservation2);

        ReservationDTO reservation3 = new ReservationDTO();
        reservation3.setName("Customer 3");
        reservation3.setPhoneNumber("0917-104-1113");
        reservation3.setEmail("customer_3@gmail.com");
        reservation3.setReservationDate(LocalDateTime.now().plusDays(1));
        reservation3.setNumberOfGuest(3);
        reservation3.setNotificationType(NotificationType.EMAIL);
        reservationService.createReservation(reservation3);

        reservationService.cancelReservation(3);
    }




}
