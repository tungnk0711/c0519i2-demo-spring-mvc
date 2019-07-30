package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
@PropertySource("classpath:global_config_app.properties")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    Environment env;

    @GetMapping("/list*")
    public ModelAndView findAll() {

        List<Product> productList = productService.findAll();

        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", productList);

        return modelAndView;

    }

    @GetMapping(value = "/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("productform", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute ProductForm productform){

        // lay ten file
        MultipartFile multipartFile = productform.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        // luu file len server
        try {
            FileCopyUtils.copy(productform.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // tham khảo: https://github.com/codegym-vn/spring-static-resources

        // tao doi tuong de luu vao db
        Product productObject = new Product(productform.getId(), productform.getName(), fileName);

        // luu vao db
        productService.addProduct(productObject);

        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("productform", new ProductForm());
        modelAndView.addObject("message","Created.");

        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView viewProduct(@PathVariable("id") Long id){

        Product product = productService.findById(id);

        ModelAndView modelAndView = new ModelAndView("/product/detail");
        modelAndView.addObject("product", product);
        return modelAndView;
    }



}
