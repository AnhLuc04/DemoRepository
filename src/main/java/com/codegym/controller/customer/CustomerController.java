package com.codegym.controller.customer;

import com.codegym.Service.CustomerService;
import com.codegym.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
//@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @GetMapping("customers")
    public ModelAndView listNation(){
        ModelAndView modelAndView = new ModelAndView("customers/list");
        Iterable<Customer>customers = customerService.findAll();
        modelAndView.addObject("customer",customers);
        return modelAndView;
    }
    @GetMapping("/create-customers")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("customers/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }
    @PostMapping("/create-customers")
    public ModelAndView saveNation(@ModelAttribute("customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("customers/create");
        customerService.save(customer);
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }
    @GetMapping("/edit-customers/{id}")
    public ModelAndView updateCity(@PathVariable Long id) {
        Optional<Customer> customer= customerService.findById(id);
            ModelAndView modelAndView = new ModelAndView("/customers/edit");
            modelAndView.addObject("customer",customer);
            return modelAndView;
    }
    @PostMapping("/edit-customers")
    public ModelAndView updateCity(@ModelAttribute("customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("customers/list");
        customerService.save(customer);
        Iterable<Customer>customers = customerService.findAll();
        modelAndView.addObject("customer",customers);
        return modelAndView;
    }
    @GetMapping("/delete-customers/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        customerService.remove(id);
        ModelAndView modelAndView = new ModelAndView("customers/list");
        Iterable<Customer>customers = customerService.findAll();
        modelAndView.addObject("customer",customers);
        return modelAndView;
    }
}
