package com.ecommerce.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.admin.dto.LoginDto;
import com.ecommerce.admin.dto.ProductcatDto;
import com.ecommerce.admin.dto.UserDto;
import com.ecommerce.admin.entity.Productcat;
import com.ecommerce.admin.service.ProductcatService;
import com.ecommerce.common.FileUploadUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("admin/category")
public class ProductcatController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductcatController.class);

	@Autowired
	ProductcatService productcatService;
	
	@GetMapping("/list-category")
	public String category(Model model) {
		return "productcat/index";
	}
	
	@GetMapping("/list-category/page")
	@ResponseBody
	public Object category() {
		try {
			List<ProductcatDto> categorydto = productcatService.findAllOrderByDatimeDesc();
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(categorydto);
			return new ResponseEntity<>(json,HttpStatus.OK);
		}catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<String>("Failed",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/add-category")
	public String add(Model model) {
		ProductcatDto category = new ProductcatDto();
		model.addAttribute("category", category);
		return "productcat/add-productcat";
	}
	
	@PostMapping("/add-category")
	public String add(@Valid @ModelAttribute("category") ProductcatDto category,
			BindingResult errors,
			Model model,
			@RequestParam("fileName") MultipartFile multipartFile) throws IOException {
		if(errors.hasErrors()) {
			model.addAttribute("message","Has errors! please fix");
			return "productcat/add-productcat";
		}
		//Get name file
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		category.setImage(fileName);
		LoginDto principal = (LoginDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		category.setUseradd(principal.getUsername());
		//Get user add
		Productcat entity = productcatService.save(category);
		
		if(entity == null) {// Category is exist
			model.addAttribute("message","Category is exist");
			return "productcat/add-productcat";
		}else {
			if(!entity.getImage().trim().isBlank()) {
				String uploadDir = "category-photos\\" + entity.getUseradd();
				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			}
		}
		return "redirect:/admin/category/list-category";
	}
	
	@GetMapping("/edit-category")
	public String edit(Model model,@RequestParam("id")int id) {
		ProductcatDto category = productcatService.findById(id);
		model.addAttribute("category", category);
		return "productcat/edit-productcat"; 
	}
	
	@PostMapping("/edit-category")
	public String edit(@Valid @ModelAttribute("category") ProductcatDto category,
			BindingResult errors,
			Model model,
			@RequestParam("fileName") MultipartFile multipartFile) throws IOException {
		if(errors.hasErrors()) {
			model.addAttribute("message","Has errors! please fix");
			return "productcat/edit-productcat";
		}
		//Get name file
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		category.setImage(fileName);
		LoginDto principal = (LoginDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		category.setUseradd(principal.getUsername());
		//Get user add
		Productcat entity = productcatService.edit(category);
		
		if(entity != null) {
			if(!entity.getImage().trim().isBlank()) {
				String uploadDir = "category-photos\\" + entity.getUseradd();
				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			}
		}
		return "redirect:/admin/category/list-category";
	}
	
	@GetMapping("/delete-category")
	@ResponseBody
	public Object deleteCategory(Model model,@RequestParam("id")int id) {
		ProductcatDto category = productcatService.findById(id);
		if(category==null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		productcatService.invalidProductcat(id);
		return new ResponseEntity<String>("Category is updated", HttpStatus.OK); 
	}
}
