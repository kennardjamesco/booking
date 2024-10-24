package com.umpisa.customer.booking.service;

import com.umpisa.customer.booking.entity.CustomerEntity;
import com.umpisa.customer.booking.dto.CustomerDTO;

import java.util.List;


public interface CustomerService {
    public List<CustomerDTO> getAllCustomer();
    public CustomerDTO saveCustomer(CustomerDTO customer);
    public CustomerDTO updateCustomerById( CustomerDTO customer);
    public CustomerDTO deleteCustomerById(CustomerDTO customer);
    public CustomerDTO findCustomerById(Integer id);
    public CustomerDTO findCustomerByEmail(String email);
    public CustomerDTO findCustomerByNumber(String number);
    public CustomerDTO customerEntityToCustomerDTO(CustomerEntity customerEntity);
    public CustomerEntity customerDTOToCustomerEntity(CustomerDTO customerDTO);

}
