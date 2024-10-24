package com.umpisa.customer.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Integer customerId;
    private String name;
    @Column(name="phone_number",unique = true)
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<ReservationEntity> reservations;


}
