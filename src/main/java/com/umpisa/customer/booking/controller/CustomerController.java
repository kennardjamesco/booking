//package com.umpisa.customer.booking.controller;
//
//import com.umpisa.customer.booking.dto.CustomerDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//import com.umpisa.customer.booking.service.CustomerService;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/customer")
//public class CustomerController {
//    private final CustomerService customerService;
//
//
//    @Transactional(readOnly = true)
//    @GetMapping("/all")
//    public ResponseEntity<List<CustomerDTO>> getAllCustomer() {
//        List<CustomerDTO> customerDTOs = customerService.getAllCustomer();
//        return ResponseEntity.ok(customerDTOs);
//    }
//
//
//    @PostMapping("/save")
//    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customerDTO){
//        customerService.saveCustomer(customerDTO);
//        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/id/{id}")
//    @Transactional(readOnly = true)
//    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
//        CustomerDTO customerDTO = customerService.findCustomerById(id);
//        return ResponseEntity.ok(customerDTO);
//    }
//    @GetMapping("/email/{email}")
//    @Transactional(readOnly = true)
//    public ResponseEntity<CustomerDTO> getCustomerByEmail(@PathVariable String email) {
//        CustomerDTO customerDTO = customerService.findCustomerByEmail(email);
//
//        return ResponseEntity.ok(customerDTO);
//    }
//
//    @GetMapping("/number/{number}")
//    @Transactional(readOnly = true)
//    public ResponseEntity<CustomerDTO> getCustomerByPhoneNumber(@PathVariable String number) {
//        CustomerDTO customerDTO = customerService.findCustomerByNumber(number);
//        return ResponseEntity.ok(customerDTO);
//    }
//}
