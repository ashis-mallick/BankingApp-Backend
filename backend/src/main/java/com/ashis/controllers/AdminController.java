package com.ashis.controllers;


import com.ashis.dto.*;
import com.ashis.entities.Customer;
import com.ashis.entities.Transactions;
import com.ashis.services.AdminService;
import jakarta.validation.Valid;
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




    @PostMapping("/change-password")
    public ChangePasswordDto changeCustomerPassword(@Valid @RequestBody AccountDto accountDto){
        return adminService.changePassword(accountDto);

    }


    @PostMapping("/delete-customer")
    public AccountDeletedDto deleteCustomer( @Valid @RequestBody  AccountDto accountDto){

      return  adminService.deleteCustomer(accountDto);

    }



    @GetMapping("/view-transactions")
    public List<Transactions> viewAllTransactions(){

      return  adminService.viewAllTransactions();


    }


}
