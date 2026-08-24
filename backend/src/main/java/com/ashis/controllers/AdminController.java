package com.ashis.controllers;


import com.ashis.dto.CustomerStatusDto;
import com.ashis.entities.Customer;
import com.ashis.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private  final  AdminService adminService;

    @GetMapping("/view")
    public List<Customer> viewAllCustomers(){
       return adminService.viewAllCustomers();


    }

    @GetMapping("/search")
    public Customer searchByCustomerAccountNo(@RequestParam String accountNo){

        return adminService.searchByAccountNo(accountNo);

    }

    @PostMapping("/change-status")
    public CustomerStatusDto changeActiveStatus(@RequestBody CustomerStatusDto customerStatusDto) {

        return adminService.changeActiveStatus(customerStatusDto);
    }



    @PostMapping("/change-block-status")
    public  void changeBlockStatus(){


    }

    @PostMapping("/change-password")
    public void changeCustomerPassword(){

    }


    @PostMapping("/delete-customer")
    public void deleteCustomer(){

    }





}
