package com.umpisa.customer.booking.service.impl;

import com.umpisa.customer.booking.entity.CustomerEntity;
import com.umpisa.customer.booking.repository.CustomerRepository;
import com.umpisa.customer.booking.dto.CustomerDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.umpisa.customer.booking.service.CustomerService;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;




    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<CustomerEntity> customerEntity = customerRepository.findAll();
        return customerEntity.stream()
                .map(this::customerEntityToCustomerDTO)
                .collect(Collectors.toList());
//        return customerDTOList;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {
        CustomerEntity customerEntity = customerDTOToCustomerEntity(customer);
        customerRepository.save(customerEntity);
        return customer;
    }

    @Override
    public CustomerDTO updateCustomerById( CustomerDTO customer) {
        CustomerDTO customerDTO = customerRepository.findByCustomerId(customer.getCustomerId());

            CustomerEntity customerEntity = customerDTOToCustomerEntity(customer);
            customerRepository.save(customerEntity);
            return customerDTO;
    }

    @Override
    public CustomerDTO deleteCustomerById( CustomerDTO customer) {
        CustomerDTO customerDTO = customerRepository.findByCustomerId(customer.getCustomerId());

            customerRepository.deleteById(customer.getCustomerId());
            return customerDTO;

    }

    @Override
    public CustomerDTO findCustomerById(Integer id) {
        CustomerDTO customerDTO = customerRepository.findByCustomerId(id);
        return customerDTO;
    }

    @Override
    public CustomerDTO findCustomerByEmail(String email) {
        CustomerDTO customerDTO = customerRepository.findByEmail(email);
        return customerDTO;
    }

    @Override
    public CustomerDTO findCustomerByNumber(String number) {
        CustomerDTO customerDTO = customerRepository.findByPhoneNumber(number);
        return customerDTO;
    }


    public CustomerDTO customerEntityToCustomerDTO(CustomerEntity customerEntity){
        CustomerDTO customerDTO = new CustomerDTO(customerEntity.getCustomerId(),
                customerEntity.getName(),
                customerEntity.getPhoneNumber(),
                customerEntity.getEmail());
//        customerDTO.setCustomerId(customerEntity.getCustomerId());
//        customerDTO.setName(customerEntity.getName());
//        customerDTO.setPhoneNumber(customerEntity.getPhoneNumber());
//        customerDTO.setEmail(customerEntity.getEmail());
        return customerDTO;
    }

    public CustomerEntity customerDTOToCustomerEntity(CustomerDTO customerDTO){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customerDTO.getCustomerId());
        customerEntity.setName(customerDTO.getName());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setPhoneNumber(customerDTO.getPhoneNumber());
        return customerEntity;
    }
}

