package com.codegym.controller.customer;

import com.codegym.exception.DuplicateLastNameException;
import com.codegym.service.CustomerService;
import com.codegym.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;


    @GetMapping("/search/{name}")
    public ResponseEntity<Iterable<Customer>> findAllByName(@PathVariable String name){
        return new ResponseEntity<>(customerService.findAllByFirstNameContaining(name), HttpStatus.OK);
    }
    @GetMapping
    public ModelAndView listCustomer() {
        ModelAndView modelAndView = new ModelAndView("customers/list");
        Iterable<Customer> customers = customerService.findAll();
        modelAndView.addObject("customer", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("customers/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
        @GetMapping("/edit/{id}")
    public ModelAndView updateCustomer(@PathVariable Long id){
        Customer customer= customerService.findById(id);
            ModelAndView modelAndView = new ModelAndView("/customers/edit");
            modelAndView.addObject("customer",customer);
            return modelAndView;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Customer> edit(@PathVariable Long id, @RequestBody Customer customer) throws DuplicateLastNameException {
        customer.setId(id);
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> delete(@PathVariable Long id) {
        customerService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/create")
//    public ModelAndView saveCustomer(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult) throws DuplicateLastNameException {
//
//            if (bindingResult.hasFieldErrors()) {
//                ModelAndView modelAndView = new ModelAndView("customers/create");
//                modelAndView.addObject("message", "Cú pháp sai bạn nhé");
//                return modelAndView;
//            }
//            ModelAndView modelAndView = new ModelAndView("customers/create");
//            customerService.save(customer);
//            modelAndView.addObject("customer", new Customer());
//            return modelAndView;
//    }
//    @GetMapping("/edit/{id}")
//    public ModelAndView updateCustomer(@PathVariable Long id){
//        Customer customer= customerService.findById(id);
//            ModelAndView modelAndView = new ModelAndView("/customers/edit");
//            modelAndView.addObject("customer",customer);
//            return modelAndView;
//    }



    //    @PostMapping("/edit")
//    public ModelAndView updateCustomer(@Validated @ModelAttribute("customer") Customer customer,BindingResult bindingResult) throws DuplicateLastNameException {
//        if (bindingResult.hasFieldErrors()) {
//            ModelAndView modelAndView = new ModelAndView("customers/create");
//            modelAndView.addObject("message", "Cú pháp sai bạn nhé");
//            return modelAndView;
//        }
//
//        ModelAndView modelAndView = new ModelAndView("customers/list");
//        customerService.save(customer);
//        Iterable<Customer>customers = customerService.findAll();
//        modelAndView.addObject("customer",customers);
//        return modelAndView;
//    }

//    @GetMapping("/delete/{id}")
//    public ModelAndView showDeleteForm(@PathVariable Long id){
//        customerService.remove(id);
//        ModelAndView modelAndView = new ModelAndView("customers/list");
//        Iterable<Customer>customers = customerService.findAll();
//        modelAndView.addObject("customer",customers);
//        return modelAndView;
//    }



//    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
//    public ResponseEntity<Customer>deleteProvince(@PathVariable Long id){
//        try{
//            provinceService.remove(id);
//            return new ResponseEntity<>((Province) provinceService.findById(id),HttpStatus.OK);
//        }
//        catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}
    @ExceptionHandler(DuplicateLastNameException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/error");
    }
}
