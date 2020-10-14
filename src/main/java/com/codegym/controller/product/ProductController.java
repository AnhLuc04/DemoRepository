package com.codegym.controller.product;


import com.codegym.Service.ProductService;
import com.codegym.model.Customer;
import com.codegym.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
public class ProductController {
    @Autowired
    ProductService productService;
//    @GetMapping
//    public ModelAndView showList(Pageable pageInfo) {
//        ModelAndView modelAndView = new ModelAndView("product/list");
//        Page<Product> products= productService.findAll(pageInfo);
//        modelAndView.addObject("product", products);
//        return modelAndView;
//    }
    @GetMapping("Product")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("product/list");
        List<Product> products= productService.findAll();
        modelAndView.addObject("product", products);
        return modelAndView;
    }
    @GetMapping("/create-product")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/create-product")
    public ModelAndView saveNation(@ModelAttribute("product") Product product){
        ModelAndView modelAndView = new ModelAndView("product/create");
        productService.save(product);
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @GetMapping("/edit-product/{id}")
    public ModelAndView updateCity(@PathVariable Long id) {
        Optional<Product> product= productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product",product);
        return modelAndView;
    }
    @PostMapping("/edit-product")
    public ModelAndView updateCity(@ModelAttribute("product")Product product){
        ModelAndView modelAndView = new ModelAndView("product/list");
        productService.save(product);
        List<Product> products= productService.findAll();
        modelAndView.addObject("product", products);
        return modelAndView;
    }
    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        productService.delete(id);
        ModelAndView modelAndView = new ModelAndView("product/list");
        List<Product> products= productService.findAll();
        modelAndView.addObject("product", products);
        return modelAndView;
    }
}
