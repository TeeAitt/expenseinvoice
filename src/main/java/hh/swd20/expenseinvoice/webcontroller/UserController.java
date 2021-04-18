package hh.swd20.expenseinvoice.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.expenseinvoice.domain.DepartmentRepository;
import hh.swd20.expenseinvoice.domain.User;
import hh.swd20.expenseinvoice.domain.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	// This functions prints all users on the userlist page.
	@RequestMapping("/userlist")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String userList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "userlist";
	}
	
	// This function prints an empty form for adding a new user.
	@RequestMapping("/adduser")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("departments", departmentRepository.findAll());
		return "newuser";
	}
	
	// This function saves the new user.
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/saveuser", method=RequestMethod.POST)
	public String saveUser(User user) {
		userRepository.save(user);
		return "redirect:userlist";
	}

}
