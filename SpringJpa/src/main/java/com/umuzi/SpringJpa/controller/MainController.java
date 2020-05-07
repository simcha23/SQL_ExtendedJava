package com.umuzi.SpringJpa.controller;

import com.umuzi.SpringJpa.dao.CustomersRepository;
import com.umuzi.SpringJpa.model.Employees;
import com.umuzi.SpringJpa.model.Customers;
import com.umuzi.SpringJpa.dao.EmployeesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        try{
            List<Customers> customers = Arrays.asList(
                    objectMapper.readValue(customersRepository.findById(1).toString() , Customers[].class)
            );
            for (Customers customer:customers) {
                System.out.println(
                                customer.getCustomerId() + "\t" +
                                customer.getFirstName() + "\t" +
                                customer.getLastName() + "\t" +
                                customer.getGender() + "\t" +
                                customer.getAddress() + "\t" +
                                customer.getEmail() + "\t" +
                                customer.getCity() + "\t" +
                                customer.getCity()
                );
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return customersRepository.findAll();
    }
    @GetMapping(path="/customerById")
    public @ResponseBody Optional<Customers> getCustomerById() {

        try{
            Customers customers = objectMapper.readValue(customersRepository.findById(1).toString() , Customers.class);
            System.out.println(
                    customers.getCustomerId() + "\t" +
                            customers.getFirstName() + "\t" +
                            customers.getLastName() + "\t" +
                            customers.getGender() + "\t" +
                            customers.getAddress() + "\t" +
                            customers.getEmail() + "\t" +
                            customers.getCity() + "\t" +
                            customers.getCity()
            );

        }catch (IOException ex){
            ex.printStackTrace();
        }
        return customersRepository.findById(1);
    }
    @GetMapping(path="/updateCustomer")
    public @ResponseBody Customers updateCustomer() {

        Customers customers = null;
        try{
            customers = customersRepository.findById(1).get();
            customers.setFirstName("Lerato");
            customers.setLastName("Mabitso");
            customersRepository.save(customers);

            customers = objectMapper.readValue(customersRepository.findById(1).toString() , Customers.class);
            System.out.println(
                            customers.getCustomerId() + "\t" +
                            customers.getFirstName() + "\t" +
                            customers.getLastName() + "\t" +
                            customers.getGender() + "\t" +
                            customers.getAddress() + "\t" +
                            customers.getEmail() + "\t" +
                            customers.getCity() + "\t" +
                            customers.getCity()
            );

        }catch (IOException ex){
            ex.printStackTrace();
        }
        return customersRepository.save(customers);

    }
}
