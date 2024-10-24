package com.umpisa.customer.booking.repository;

import com.umpisa.customer.booking.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity,Integer> {
    List<ReservationEntity> findAllByCustomerEmail(String email);

    Optional<ReservationEntity> findByReservationId(Integer reservationId);
    List<ReservationEntity> findAllByReservationDateBetweenAndStatus(LocalDateTime start, LocalDateTime end, String status);
}
