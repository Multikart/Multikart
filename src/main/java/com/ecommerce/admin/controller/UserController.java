package com.ecommerce.admin.controller;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.admin.dao.UserDAO;
import com.ecommerce.admin.dto.RoleDto;
import com.ecommerce.admin.dto.UserDto;
import com.ecommerce.admin.entity.User;
import com.ecommerce.admin.service.RoleService;
import com.ecommerce.admin.service.UserService;
import com.ecommerce.common.FileUploadUtil;

@Controller
@RequestMapping("admin/user")
public class UserController {
	private List<RoleDto> listRoleDto;

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
		listRoleDto = roleService.getListRole();
		if(errors.hasErrors()) {
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
		if(!userdto.getPassword().isBlank()) {
			if(!userdto.getPassword().equals(userdto.getConfirmpassword())&&!userdto.getConfirmpassword().isBlank()) {
				if(!listRoleDto.isEmpty()) {
					model.addAttribute("listRoleDto", listRoleDto);
				}
				errors.rejectValue("confirmpassword", "user", "Password does not match");
				return "user/add-user";
			}
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

		return "redirect:/admin/user/list-user/1";
	}
	
	@RequestMapping("/list-user/{pageIndex}")
	public String index(@PathVariable("pageIndex") int pageIndex ,Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("message", "abc");
//		int pageSize = 1;
//		Pageable pageable = PageRequest.of(index, pageSize);
//		Page page = userService.findAllPaging(pageable);
//		System.out.print(page.getContent());
//		List<User> userList = new ArrayList<User>(page.getContent());
//		for(User user : userList) {
//			System.out.print(user.getEmail());
//		}
//		model.addAttribute("users", page);
		int pageSize = 10;
		Page<User> userPage = userService.pagingUserDto(pageIndex, pageSize);
		List<User> listUser = userPage.getContent();
		model.addAttribute("listUser", listUser);
		model.addAttribute("currentPage", pageIndex);
		model.addAttribute("totalPages", userPage.getTotalPages());
		model.addAttribute("totalElement", userPage.getTotalElements());
		return "user/list-user";
	}
	
	//delete User
	@GetMapping("delete-user")
	public String deleteUser(@RequestParam(name = "id") int id, Model model) {
		userService.delete(id);
		return "redirect:/admin/user/list-user/1";
	}
	
	//Edit User
	@GetMapping("edit-user")
	public String editUser(@RequestParam(name = "id") int id, @RequestParam(name = "page") int page, Model model) {
		
		UserDto userDto = userService.findById(id);
		model.addAttribute("user", userDto);
		List<RoleDto> listRoleDto = roleService.getListRole();
		if(!listRoleDto.isEmpty()) {
			model.addAttribute("listRoleDto", listRoleDto);
			model.addAttribute("page", page);
		}
		
		return "/user/edit-user";
	}
	
	//Update User
	@PostMapping("edit-user")
	public String updateUser(@Valid @ModelAttribute("user") UserDto userdto, BindingResult errors, Model model,  @RequestParam("fileName") MultipartFile muiMultipartFile , @RequestParam(name="page") String page ) throws IOException {
		listRoleDto = roleService.getListRole();
		if(errors.hasErrors()) {
			if(!listRoleDto.isEmpty()) {
				model.addAttribute("listRoleDto", listRoleDto);
			}
			
			if(!userdto.getPassword().isBlank()) {
				if(!userdto.getPassword().equals(userdto.getConfirmpassword())&&!userdto.getConfirmpassword().isBlank()) {
					errors.rejectValue("confirmpassword", "user", "Password does not match");
				}
			}
			
			return "redirect:/admin/user/edit-user?id=" + userdto.getId() + "&page=" + page;
		}
		if(!userdto.getPassword().isBlank()) {
			if(!userdto.getPassword().equals(userdto.getConfirmpassword())&&!userdto.getConfirmpassword().isBlank()) {
				if(!listRoleDto.isEmpty()) {
					model.addAttribute("listRoleDto", listRoleDto);
				}
				errors.rejectValue("confirmpassword", "user", "Password does not match");
				return "redirect:/admin/user/edit-user?id=" + userdto.getId() + "&page=" + page;
			}
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
		return "redirect:/admin/user/list-user/" + page;
	}
}
