package com.umpisa.customer.booking.service;


import com.umpisa.customer.booking.dto.ReservationDTO;
import com.umpisa.customer.booking.dto.ReservationDTO;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    ReservationDTO createReservation(ReservationDTO customerReservationDTO);
    ReservationDTO cancelReservation(Integer id);
    List<ReservationDTO> getAllReservationsByEmail(String email);
    ReservationDTO  updateReservation(Integer id,ReservationDTO updatedReservationDTO);
    List<ReservationDTO> getAllReservation();
}
