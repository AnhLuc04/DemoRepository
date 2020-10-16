package com.codegym.controller.product;


import com.codegym.exception.DuplicateLastNameException;
import com.codegym.service.ProductService;
import com.codegym.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductService productService;
//    @GetMapping("Product")
//    public ModelAndView showList(Pageable pageInfo) {
//        Page<Product> products;
//        products = productService.findAll(pageInfo);
//        modelAndView.addObject("product", products);
//        return modelAndView;
  //  }
    @GetMapping
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("product/list");
        List<Product> products= productService.findAll();
        modelAndView.addObject("product", products);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) throws DuplicateLastNameException {
            new Product().validate(product, bindingResult);
            if (bindingResult.hasFieldErrors()) {
                ModelAndView modelAndView = new ModelAndView("product/create");
                return modelAndView;
            }
            ModelAndView modelAndView = new ModelAndView("product/create");
            productService.save(product);
            modelAndView.addObject("product", new Product());
            return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView updateProduct(@PathVariable Long id) throws Exception {
        Optional<Product> product= productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product",product);
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) throws DuplicateLastNameException {
        new Product().validate(product, bindingResult);
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("product/edit");
            modelAndView.addObject("message", "Cú pháp sai bạn nhé");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("product/list");
        productService.save(product);
        List<Product> products= productService.findAll();
        modelAndView.addObject("product", products);
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        productService.delete(id);
        ModelAndView modelAndView = new ModelAndView("product/list");
        List<Product> products= productService.findAll();
        modelAndView.addObject("product", products);
        return modelAndView;
    }
    @GetMapping("/Product/{id}")
    public ModelAndView showLists(@PathVariable Long id) throws Exception {
        ModelAndView modelAndView= new ModelAndView("product/listProduct");
        Optional<Product> product = productService.findById(id);
        modelAndView.addObject("product", product.get());
        return modelAndView;
    }
    @GetMapping("{id}")
    public ModelAndView showInformation(@PathVariable Long id) throws Exception {
            ModelAndView modelAndView = new ModelAndView("product/create");
            Optional<Product> product= null;
           product = productService.findById(id);
            modelAndView.addObject("product",product);
            return modelAndView;
    }
    @ExceptionHandler(DuplicateLastNameException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/error");
    }
}
