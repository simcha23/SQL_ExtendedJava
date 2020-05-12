package com.umuzi.SpringJpa.controller;

import com.umuzi.SpringJpa.dao.CustomersRepository;
import com.umuzi.SpringJpa.model.Customers;
import com.umuzi.SpringJpa.dao.EmployeesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Controller // This means that this class is a Controller
@RequestMapping(path="/umuzi") // This means URL's start with /SpringJpa (after Application path)
public class MainController {
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(path="/allCustomers")
    public @ResponseBody Iterable<Customers> getAllCustomers() {

        Iterable<Customers> customers = customersRepository.findAll();
        for (Customers customer : customers) {
            System.out.println(
                    customer.getCustomerId() + "\t" +
                            customer.getFirstName() + "\t" +
                            customer.getLastName() + "\t" +
                            customer.getGender() + "\t" +
                            customer.getAddress() + "\t" +
                            customer.getEmail() + "\t" +
                            customer.getCity() + "\t" +
                            customer.getCountry()
            );
        }

        return customersRepository.findAll();
    }
    @GetMapping(path="/customerById")
    public Optional<Customers> getCustomerById() {

        Optional<Customers> customer = customersRepository.findById(3);
        if(customer.isPresent()){
            Customers existingCustomer = customer.get();
            System.out.println(
                    existingCustomer.getCustomerId() + "\t" +
                            existingCustomer.getFirstName() + "\t" +
                            existingCustomer.getLastName() + "\t" +
                            existingCustomer.getGender() + "\t" +
                            existingCustomer.getAddress() + "\t" +
                            existingCustomer.getEmail() + "\t" +
                            existingCustomer.getCity() + "\t" +
                            existingCustomer.getCountry()
            );
        }else {
            System.out.println("Selected customer not available!");
        }

        return customersRepository.findById(1);
    }
    @GetMapping(path="/updateCustomer")
    public @ResponseBody Customers updateCustomer() {

        Customers customers = null;

            customers = customersRepository.findById(1).get();
            customers.setFirstName("Lerato");
            customers.setLastName("Mabitso");
            customersRepository.save(customers);

            System.out.println(
                    customers.getCustomerId() + "\t" +
                            customers.getFirstName() + "\t" +
                            customers.getLastName() + "\t" +
                            customers.getGender() + "\t" +
                            customers.getAddress() + "\t" +
                            customers.getEmail() + "\t" +
                            customers.getCity() + "\t" +
                            customers.getCountry()

            );

        return customersRepository.save(customers);

    }
//    @GetMapping(path="/deleteCustomer")
//    public @ResponseBody void deleteCustomer() {
//
//        Customers customers = null;
//
//            String nn = customersRepository.deleteById(2);
//            assert(customersRepository).equals(1);
//            customers.setFirstName("Lerato");
//            customers.setLastName("Mabitso");
//            customersRepository.save(customers);
//
//
//            System.out.println(
//                    customers.getCustomerId() + "\t" +
//                            customers.getFirstName() + "\t" +
//                            customers.getLastName() + "\t" +
//                            customers.getGender() + "\t" +
//                            customers.getAddress() + "\t" +
//                            customers.getEmail() + "\t" +
//                            customers.getCity() + "\t" +
//                            customers.getCity()
//            );
//
//
//       return customersRepository.save(customers);
//
//    }
}
