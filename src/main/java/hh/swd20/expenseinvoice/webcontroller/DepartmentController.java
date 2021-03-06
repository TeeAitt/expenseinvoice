package hh.swd20.expenseinvoice.webcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.expenseinvoice.domain.Department;
import hh.swd20.expenseinvoice.domain.DepartmentRepository;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	// This function prints all departments on the deptlist page. It also prints an empty form for adding a new department.
	@RequestMapping("/deptlist")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String department(Model model) {
		model.addAttribute("departments", departmentRepository.findAll());
		model.addAttribute("department", new Department());
		return "deptlist";
	}
	
	// This function prints existing department information, that can then be edited.
	@RequestMapping(value="/edit_dept/{id}", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editDept(@PathVariable(value="id") Long deptId, Model model) {
		model.addAttribute("department", departmentRepository.findById(deptId));
		return "editdept";
	}
	
	// This function saves the added/edited department. It also checks with @Valid that the inserted data is in correct format.
	@RequestMapping(value="/savedept", method=RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveDept(@Valid Department department, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "deptlist";
		} else {
		departmentRepository.save(department);
		return "redirect:deptlist";
		}
	}
	
	// This function deletes a department.
	@RequestMapping(value="/delete_dept/{id}", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteDept(@PathVariable(value="id") Long deptId) {
		departmentRepository.deleteById(deptId);
		return "redirect:../deptlist";
	}
	
	
	/************************* RESTful services below ****************************
	 */
	
	// This REST function will get all the departments.
	@RequestMapping(value="/departments", method=RequestMethod.GET)
	public @ResponseBody List<Department> deptListRest() {
		return (List<Department>) departmentRepository.findAll();
	}
	
	// This REST function will get a department by its id.
	@RequestMapping(value="/departments/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Department> findDeptRest(@PathVariable("id") Long deptId) {
		return departmentRepository.findById(deptId);
	}
	
	// This REST function will save a new department.
	@RequestMapping(value="/departments", method=RequestMethod.POST)
	public @ResponseBody Department saveDeptRest(@RequestBody Department department) {
		return departmentRepository.save(department);
	}

}
