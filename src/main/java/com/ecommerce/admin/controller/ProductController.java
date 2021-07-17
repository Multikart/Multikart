package com.ecommerce.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/product")
public class ProductController {
	
	@RequestMapping("add-product")
	public ModelAndView productDetail() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("product/add-product");
		return modelAndView;
	}
}
