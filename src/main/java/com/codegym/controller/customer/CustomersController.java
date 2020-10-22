package com.codegym.controller.customer;

import com.codegym.exception.DuplicateLastNameException;
import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customers")
public class CustomersController{

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ModelAndView listStudent(){
        ModelAndView modelAndView = new ModelAndView("customers/ListCustomers");
        modelAndView.addObject("customer",customerService.findAll());
        modelAndView.addObject("customers", new Customer());
        return  modelAndView;
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Iterable<Customer>> findAll(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Iterable<Customer>> findAllByName(@PathVariable String name){
        return new ResponseEntity<>(customerService.findAllByFirstNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable Long id) {
        customerService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Customer> edit(@PathVariable Long id, @RequestBody Customer customer) throws DuplicateLastNameException {
        customer.setId(id);
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
