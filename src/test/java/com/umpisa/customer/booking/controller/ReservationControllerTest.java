package com.umpisa.customer.booking.controller;

import com.umpisa.customer.booking.dto.ReservationDTO;
import com.umpisa.customer.booking.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createReservation_Success() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setName("John Doe");
        reservationDTO.setPhoneNumber("0917-500-1233");

        when(reservationService.createReservation(any(ReservationDTO.class))).thenReturn(reservationDTO);

        ResponseEntity<ReservationDTO> response = reservationController.createReservation(reservationDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(reservationDTO, response.getBody());
    }

    @Test
    void updateReservation_Success() {
        Integer id = 1;
        ReservationDTO updatedReservationDTO = new ReservationDTO();
        updatedReservationDTO.setName("Jane Doe");

        when(reservationService.updateReservation(eq(id), any(ReservationDTO.class))).thenReturn(updatedReservationDTO);

        ResponseEntity<ReservationDTO> response = reservationController.updateReservation(id, updatedReservationDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedReservationDTO, response.getBody());
    }

    @Test
    void updateReservation_NotFound() {
        Integer id = 1;
        ReservationDTO updatedReservationDTO = new ReservationDTO();

        when(reservationService.updateReservation(eq(id), any(ReservationDTO.class))).thenReturn(null);

        ResponseEntity<ReservationDTO> response = reservationController.updateReservation(id, updatedReservationDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void cancelReservation_Success() {
        Integer id = 1;
        ReservationDTO canceledReservation = new ReservationDTO();
        canceledReservation.setStatus("CANCELLED");

        when(reservationService.cancelReservation(eq(id))).thenReturn(canceledReservation);

        ResponseEntity<ReservationDTO> response = reservationController.cancelReservation(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(canceledReservation, response.getBody());
    }

    @Test
    void cancelReservation_NotFound() {
        Integer id = 1;

        when(reservationService.cancelReservation(eq(id))).thenReturn(null);

        ResponseEntity<ReservationDTO> response = reservationController.cancelReservation(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllReservationsByEmail_Success() {
        String email = "john@example.com";
        List<ReservationDTO> reservations = Arrays.asList(new ReservationDTO(), new ReservationDTO());

        when(reservationService.getAllReservationsByEmail(eq(email))).thenReturn(reservations);

        ResponseEntity<List<ReservationDTO>> response = reservationController.getAllReservationsByEmail(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
    }

    @Test
    void getAllReservation_Success() {
        List<ReservationDTO> reservations = Arrays.asList(new ReservationDTO(), new ReservationDTO());

        when(reservationService.getAllReservation()).thenReturn(reservations);

        ResponseEntity<List<ReservationDTO>> response = reservationController.getAllReservation();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reservations, response.getBody());
    }
}
