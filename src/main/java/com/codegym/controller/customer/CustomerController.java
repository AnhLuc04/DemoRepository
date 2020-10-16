package com.codegym.controller.customer;

import com.codegym.exception.DuplicateLastNameException;
import com.codegym.service.CustomerService;
import com.codegym.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping
    public ModelAndView listCustomer(){
        ModelAndView modelAndView = new ModelAndView("customers/list");
        Iterable<Customer>customers = customerService.findAll();
        modelAndView.addObject("customer",customers);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("customers/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView saveCustomer(@Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult) throws DuplicateLastNameException {

            if (bindingResult.hasFieldErrors()) {
                ModelAndView modelAndView = new ModelAndView("customers/create");
                modelAndView.addObject("message", "Cú pháp sai bạn nhé");
                return modelAndView;
            }
            ModelAndView modelAndView = new ModelAndView("customers/create");
            customerService.save(customer);
            modelAndView.addObject("customer", new Customer());
            return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView updateCustomer(@PathVariable Long id){
        Customer customer= customerService.findById(id);
            ModelAndView modelAndView = new ModelAndView("/customers/edit");
            modelAndView.addObject("customer",customer);
            return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateCustomer(@Validated @ModelAttribute("customer") Customer customer,BindingResult bindingResult) throws DuplicateLastNameException {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("customers/create");
            modelAndView.addObject("message", "Cú pháp sai bạn nhé");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("customers/list");
        customerService.save(customer);
        Iterable<Customer>customers = customerService.findAll();
        modelAndView.addObject("customer",customers);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        customerService.remove(id);
        ModelAndView modelAndView = new ModelAndView("customers/list");
        Iterable<Customer>customers = customerService.findAll();
        modelAndView.addObject("customer",customers);
        return modelAndView;
    }
    @ExceptionHandler(DuplicateLastNameException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/error");
    }
}
