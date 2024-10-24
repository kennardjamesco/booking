package com.umpisa.customer.booking.repository;

import com.umpisa.customer.booking.entity.CustomerEntity;
import com.umpisa.customer.booking.entity.ReservationEntity;
import com.umpisa.customer.booking.service.impl.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReservationRepositoryTest {

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllByCustomerEmail_Success() {
        String email = "john@gmail.com";

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("John Smith");
        customerEntity.setPhoneNumber("0917-500-5000");
        customerEntity.setEmail(email);

        List<ReservationEntity> reservationEntityList = new ArrayList<>();
        ReservationEntity reservation1 = new ReservationEntity();
        reservation1.setReservationId(1);
        reservation1.setCustomer(customerEntity);
        reservationEntityList.add(reservation1);

        when(reservationRepository.findAllByCustomerEmail(email)).thenReturn(reservationEntityList);

        List<ReservationEntity> result = reservationRepository.findAllByCustomerEmail(email);

        assertEquals(reservationEntityList.size(), result.size());
        assertEquals(reservationEntityList.get(0).getReservationId(), result.get(0).getReservationId());
        assertEquals(reservationEntityList.get(0).getCustomer().getEmail(), result.get(0).getCustomer().getEmail());
    }
}
