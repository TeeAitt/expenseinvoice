package hh.swd20.expenseinvoice.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.expenseinvoice.domain.Department;
import hh.swd20.expenseinvoice.domain.DepartmentRepository;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	// This function prints all departments on the deptlist page. It also prints an empty form for adding a new department.
	@RequestMapping("/deptlist")
	public String department(Model model) {
		model.addAttribute("departments", departmentRepository.findAll());
		model.addAttribute("department", new Department());
		return "deptlist";
	}
	
	// This function prints existing department information, that can then be edited.
	@RequestMapping(value="/edit_dept/{id}", method=RequestMethod.GET)
	public String editDept(@PathVariable(value="id") Long deptId, Model model) {
		model.addAttribute("department", departmentRepository.findById(deptId));
		return "editdept";
	}
	
	// This function saves the added/edited department.
	@RequestMapping(value="/savedept", method=RequestMethod.POST)
	public String saveDept(Department department) {
		departmentRepository.save(department);
		return "redirect:deptlist";
	}
	
	// This function deletes a department.
	@RequestMapping(value="/delete_dept/{id}", method=RequestMethod.GET)
	public String deleteDept(@PathVariable(value="id") Long deptId) {
		departmentRepository.deleteById(deptId);
		return "redirect:../deptlist";
	}
	

}
