package com.umpisa.customer.booking.controller;

import com.umpisa.customer.booking.dto.ReservationDTO;
import com.umpisa.customer.booking.dto.ReservationDTO;
import com.umpisa.customer.booking.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(
            @PathVariable Integer id,
            @RequestBody ReservationDTO updatedReservationDTO) {
        ReservationDTO updatedReservation = reservationService.updateReservation(id, updatedReservationDTO);
        return updatedReservation != null ?
                ResponseEntity.ok(updatedReservation) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable Integer id) {
        ReservationDTO canceledReservation = reservationService.cancelReservation(id);
        return canceledReservation != null ?
                ResponseEntity.ok(canceledReservation) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsByEmail(@PathVariable String email) {
        List<ReservationDTO> reservationDTO = reservationService.getAllReservationsByEmail(email);
        return ResponseEntity.ok(reservationDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReservationDTO>> getAllReservation() {
        List<ReservationDTO> reservationDTO = reservationService.getAllReservation();
        return ResponseEntity.ok(reservationDTO);
    }
}
