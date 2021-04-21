package hh.swd20.expenseinvoice.webcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	// This function prints existing user information, that can then be edited.
	@RequestMapping("/edit_user/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editUser(@PathVariable(value="id") Long userId, Model model) {
		model.addAttribute("user", userRepository.findById(userId));
		model.addAttribute("departments", departmentRepository.findAll());
		return "edituser";
	}
	
	// This function saves the added/edited user.
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/saveuser", method=RequestMethod.POST)
	public String saveUser(User user) {
		userRepository.save(user);
		return "redirect:userlist";
	}
	
	
	/************************* RESTful services below ****************************
	 */
	
	// This REST function will get all the users.
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public @ResponseBody List<User> userListRest() {
		return (List<User>) userRepository.findAll();
	}
	
	// This REST function will get a user by its id.
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<User> findUserRest(@PathVariable("id") Long userId) {
		return userRepository.findById(userId);
	}
	
	// This REST function will save a new user.
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public @ResponseBody User saveUserRest(@RequestBody User user) {
		return userRepository.save(user);
	}

}
