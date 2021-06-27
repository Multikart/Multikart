package com.ecommerce.admin.controller;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.admin.dto.RoleDto;
import com.ecommerce.admin.dto.UserDto;
import com.ecommerce.admin.entity.User;
import com.ecommerce.admin.service.RoleService;
import com.ecommerce.admin.service.UserService;
import com.ecommerce.common.FileUploadUtil;

@Controller
@RequestMapping("admin/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("/add-user")
	public String user(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		List<RoleDto> listRoleDto = roleService.getListRole();
		if(!listRoleDto.isEmpty()) {
			model.addAttribute("listRoleDto", listRoleDto);
		}
		return "user/add-user";
	}
	
	@PostMapping("/add-user")
	public String user(@Valid @ModelAttribute("user") UserDto userdto, BindingResult errors, Model model,  @RequestParam("fileName") MultipartFile muiMultipartFile ) throws IOException {
		
		if(errors.hasErrors()) {
			List<RoleDto> listRoleDto = roleService.getListRole();
			if(!listRoleDto.isEmpty()) {
				model.addAttribute("listRoleDto", listRoleDto);
			}
			
			if(!userdto.getPassword().isBlank()) {
				if(!userdto.getPassword().equals(userdto.getConfirmpassword())&&!userdto.getConfirmpassword().isBlank()) {
					errors.rejectValue("confirmpassword", "user", "Password does not match");
				}
			}
			
			return "user/add-user";
		}
		
		//store Name image to Database
		String fileName = StringUtils.cleanPath(muiMultipartFile.getOriginalFilename());
		userdto.setAvatar(fileName);
		User userSave =  userService.save(userdto);
		
		if(userSave.getAvatar().trim().length() != 0) {
			//create folder to save Image to static resource = user-photos/27062021/image-girl.jpg
			String uploadDir = "user-photos\\" + userSave.getJoindate();
			//Copy image to folder image 
			FileUploadUtil.saveFile(uploadDir, fileName, muiMultipartFile);
		}

		return "redirect:/admin";
	}
	
	@RequestMapping("/list-user")
	public String index(Model model) {
//		int pageSize = 1;
//		Pageable pageable = PageRequest.of(index, pageSize);
//		Page page = userService.findAllPaging(pageable);
//		System.out.print(page.getContent());
//		List<User> userList = new ArrayList<User>(page.getContent());
//		for(User user : userList) {
//			System.out.print(user.getEmail());
//		}
//		model.addAttribute("users", page);
		return "user/index";
	}
}
