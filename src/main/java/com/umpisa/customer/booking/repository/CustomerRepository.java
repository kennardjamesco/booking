package com.umpisa.customer.booking.repository;

import com.umpisa.customer.booking.entity.CustomerEntity;
import com.umpisa.customer.booking.dto.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    @Query("""
        select new com.umpisa.customer.booking.dto.CustomerDTO(c.customerId, c.name, c.phoneNumber, c.email)
        from CustomerEntity c
        where c.customerId =:id
        """)
    CustomerDTO findByCustomerId(Integer id);

    @Query("""
        select new com.umpisa.customer.booking.dto.CustomerDTO(c.customerId, c.name, c.phoneNumber, c.email)
        from CustomerEntity c
        where c.email =:email
        """)
    CustomerDTO findByEmail(String email);

    @Query("""
        select new com.umpisa.customer.booking.dto.CustomerDTO(c.customerId, c.name, c.phoneNumber, c.email)
        from CustomerEntity c
        where c.phoneNumber =:number
        """)
    CustomerDTO findByPhoneNumber(String number);

    @Query("""
        select new com.umpisa.customer.booking.dto.CustomerDTO(c.customerId, c.name, c.phoneNumber, c.email)
        from CustomerEntity c
        where c.phoneNumber =:number
        """)
    Optional<CustomerDTO> findByPhoneNumberOptional(String number);
//    CustomerEntity findByPhoneNumberEntity(String number);





}
