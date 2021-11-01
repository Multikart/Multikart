package com.ecommerce.admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.EntityResponse;

import com.ecommerce.admin.dto.RoleDto;
import com.ecommerce.admin.dto.RoleThreadUpload;
import com.ecommerce.admin.entity.Role;
import com.ecommerce.admin.service.RoleService;
import com.ecommerce.admin.service.impl.RoleServiceImpl;
import com.ecommerce.admin.service.impl.UploadRoleImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("admin/role")
public class RoleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	private static final int pagesize = 5;
	@Autowired
	RoleService roleService;
	@Autowired
	UploadRoleImpl upload;
	
	@GetMapping("/list-role")
	public String role(Model model) {
		return "role/index";
	}
	
	@ResponseBody
	@GetMapping("/list-role/page")
	public Object role() {
		try {
			List<RoleDto> roles = roleService.findAllOrderByDatimeDesc();
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(roles);
			return new ResponseEntity<>(json, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping("/add-role")
	public String addRole(Model model) {
		model.addAttribute("role", new RoleDto());
		return "role/add-role";
	}
	
	@PostMapping("/add-role")
	public String addRole(@Valid @ModelAttribute("role") RoleDto role, BindingResult errors, Model model) {
		//Validation
		if(errors.hasErrors()) {
			model.addAttribute("message", "Has Errors! Please fix");
			return "role/add-role";
		}
		RoleDto roledto = null;
		roledto = roleService.findByName(role.getName());
		if(roledto!=null) {//ROLE IS EXIST
			model.addAttribute("message", "Role is exist!");
			return "role/add-role";
		}
		String message = "";
		Role success = roleService.save(role);
		if(success != null) {
			return "redirect:/admin/role/list-role";
		}else {
			message = "Create unsuccessfully!";
			model.addAttribute("role", new RoleDto());
			model.addAttribute("message", message);
			return "role/add-role";
		}
	}
	
	@GetMapping("/edit-role")
	public String editRole(@RequestParam("id") int id, Model model) {
		RoleDto role = roleService.findById(id);
		model.addAttribute("role", role);
		return "role/edit-role";
	}
	
	@PostMapping("/edit-role")
	public String editRole(@Valid @ModelAttribute("role") RoleDto role, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			model.addAttribute("message", "Has Errors! Please fix!");
			return "role/edit-role";
		}
		RoleDto roledto = null;
		roledto = roleService.findByName(role.getName());
		if(roledto==null) {//ROLE IS NOT EXIST
			model.addAttribute("message", "Role is not exist!");
			return "role/add-role";
		}
		String message = "";
		Role success = roleService.edit(role);
		if(success != null) {
			return "redirect:/admin/role/list-role";
		}else {
			message = "Edit unsuccessfully!";
			model.addAttribute("role", new RoleDto());
			model.addAttribute("message", message);
			return "role/edit-role";
		}
	}
	
	@GetMapping("/delete-role")
	@ResponseBody
	public Object deleteRole(@RequestParam("id") int id) {
		RoleDto role = roleService.findById(id);
		if(role==null) {// ROLE IS NOT EXIST
			return new ResponseEntity<String>("Role is not exist",HttpStatus.BAD_REQUEST);
		}
		roleService.invalidRole(id);
		return new ResponseEntity<String>("Role is updated",HttpStatus.OK);
	}
	
	@GetMapping("/upload-role")
	public String roleUploadCSV(Model model) {
		LOGGER.info("upload-role");
		return "role/upload-role";
	}
	
	@PostMapping("/upload-role")
	public String roleUploadCSV(@RequestParam("fileName") MultipartFile uploadFile) {
		try {
			roleService.uploadRole(uploadFile);
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return "role/upload-role";
	}
	
}
