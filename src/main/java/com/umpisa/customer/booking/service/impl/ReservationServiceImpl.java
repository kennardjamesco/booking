package com.umpisa.customer.booking.service.impl;
import com.umpisa.customer.booking.entity.CustomerEntity;
import com.umpisa.customer.booking.entity.NotificationType;
import com.umpisa.customer.booking.entity.ReservationEntity;
import com.umpisa.customer.booking.exception.NullPointerException;
import com.umpisa.customer.booking.repository.CustomerRepository;
import com.umpisa.customer.booking.repository.ReservationRepository;
import com.umpisa.customer.booking.dto.CustomerDTO;
import com.umpisa.customer.booking.dto.ReservationDTO;
import com.umpisa.customer.booking.service.CustomerService;
import com.umpisa.customer.booking.service.EmailService;
import com.umpisa.customer.booking.service.ReservationService;
import com.umpisa.customer.booking.service.SMSService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final CustomerService customerService;
    private final SMSService smsService;
    private final EmailService emailService;

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        try{
            CustomerDTO customer = saveNewEmployee(reservationDTO);
            ReservationEntity reservationEntity = reservationDTOToReservationEntity(reservationDTO);
            reservationEntity.setStatus("CONFIRMED");
            CustomerEntity customerEntity = customerService.customerDTOToCustomerEntity(customer);
            reservationEntity.setCustomer(customerEntity);
            ReservationEntity savedReservation = reservationRepository.save(reservationEntity);

            sendNotification(savedReservation, "confirmed");


            return reservationEntityToReservationDTO(savedReservation);

        } catch(NullPointerException e)
        {
            throw new NullPointerException("Fill out required fields");
        }

    }

    public CustomerDTO saveNewEmployee(ReservationDTO reservationDTO){
        Optional<CustomerDTO> customerDTO = customerRepository.findByPhoneNumberOptional(reservationDTO.getPhoneNumber());

        CustomerDTO customer = reservationDTOToCustomerDTO(reservationDTO);
        CustomerEntity customerEntity = new CustomerEntity();
        if(customerDTO.isEmpty())
        {
            customerEntity = CustomerEntity.builder()
                    .name(reservationDTO.getName())
                    .phoneNumber(reservationDTO.getPhoneNumber())
                    .email(reservationDTO.getEmail())
                    .build();
            customerEntity = customerRepository.save(customerEntity);
        }
        return customerService.customerEntityToCustomerDTO(customerEntity);

    }



    @Override
    public ReservationDTO cancelReservation(Integer id) {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            ReservationEntity reservationEntity = optionalReservation.get();
            reservationEntity.setStatus("CANCELLED");
            reservationEntity = reservationRepository.save(reservationEntity);

            sendNotification(reservationEntity, "cancelled");

            return reservationEntityToReservationDTO(reservationEntity);
        }
        return null;
    }

    @Override
    public List<ReservationDTO> getAllReservationsByEmail(String email) {
        List<ReservationEntity> reservationEntity = reservationRepository.findAllByCustomerEmail(email);
        return reservationEntity.stream()
                .map(this::reservationEntityToReservationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO updateReservation(Integer id, ReservationDTO updatedReservationDTO) {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findByReservationId(id);
        if (optionalReservation.isPresent()) {
            ReservationEntity reservation = optionalReservation.get();
            reservation.setReservationDate(updatedReservationDTO.getReservationDate());
            reservation.setNumberOfGuest(updatedReservationDTO.getNumberOfGuest());
           ReservationEntity reservationEntity = reservationRepository.save(reservation);

            sendNotification(reservationEntity, "updated");

            return reservationEntityToReservationDTO(reservationEntity);
        }
        return null; // Handle not found case as needed
    }

    @Override
    public List<ReservationDTO> getAllReservation() {
        List<ReservationEntity> reservationEntity = reservationRepository.findAll();
        return reservationEntity.stream()
                .map(this::reservationEntityToReservationDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO reservationDTOToCustomerDTO(ReservationDTO reservationDTO){
        CustomerDTO customerDTO = new CustomerDTO(reservationDTO.getCustomerId(),
                reservationDTO.getName(),
                reservationDTO.getPhoneNumber(),
                reservationDTO.getEmail());
        return customerDTO;
    }



    public ReservationDTO reservationEntityToReservationDTO(ReservationEntity reservationEntity){
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerId(reservationEntity.getCustomer().getCustomerId());
        reservationDTO.setName(reservationEntity.getCustomer().getName());
        reservationDTO.setPhoneNumber(reservationEntity.getCustomer().getPhoneNumber());
        reservationDTO.setEmail(reservationEntity.getCustomer().getEmail());
        reservationDTO.setReservationId(reservationEntity.getReservationId());
        reservationDTO.setReservationDate(reservationEntity.getReservationDate());
        reservationDTO.setNumberOfGuest(reservationEntity.getNumberOfGuest());
        reservationDTO.setStatus(reservationEntity.getStatus());
        reservationDTO.setNotificationType(reservationEntity.getNotificationType());
        return reservationDTO;
    }

    public ReservationEntity reservationDTOToReservationEntity(ReservationDTO reservationDTO){
        ReservationEntity reservationEntity = new ReservationEntity();
        CustomerEntity customerEntity = CustomerEntity.builder()
                        .customerId(reservationDTO.getCustomerId())
                        .name(reservationDTO.getName())
                        .phoneNumber(reservationDTO.getPhoneNumber())
                        .email(reservationDTO.getEmail())
                        .build();
        reservationEntity.setCustomer(customerEntity);
        reservationEntity.setReservationId(reservationDTO.getReservationId());
        reservationEntity.setReservationDate(reservationDTO.getReservationDate());
        reservationEntity.setNumberOfGuest(reservationDTO.getNumberOfGuest());
        reservationEntity.setStatus(reservationDTO.getStatus());
        reservationEntity.setNotificationType(reservationDTO.getNotificationType());
        return reservationEntity;
    }

    private void sendNotification(ReservationEntity reservationEntity, String action) {
        String message = "Your reservation on " + reservationEntity.getReservationDate() + " is " + action + ".";
        NotificationType notificationType = reservationEntity.getNotificationType();

        if (notificationType == NotificationType.EMAIL) {
             emailService.sendMessage(message );
        }

        if (notificationType == NotificationType.SMS ) {
            smsService.sendMessage(message);
        }
    }



    @Scheduled(fixedRate = 600000)
    public void sendReservationReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reminderTime = now.plusHours(4);
        LocalDateTime reminderTimeMinutes = reminderTime.plusMinutes(10);
        List<ReservationEntity> reservations = reservationRepository.findAllByReservationDateBetweenAndStatus(reminderTime, reminderTimeMinutes,"CONFIRMED");
        for (ReservationEntity reservation : reservations) {
            sendReminder(reservation);
        }
    }

    private void sendReminder(ReservationEntity reservation) {
        String message = "Reminder: Your reservation on " + reservation.getReservationDate() + " is coming up.";
        smsService.sendMessage(message);
        // Assuming you have an email service to send emails
        emailService.sendMessage(message);
    }



}
