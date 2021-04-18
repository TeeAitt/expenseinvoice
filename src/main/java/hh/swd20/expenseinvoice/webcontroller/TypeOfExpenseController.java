package hh.swd20.expenseinvoice.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.expenseinvoice.domain.TypeOfExpense;
import hh.swd20.expenseinvoice.domain.TypeOfExpenseRepository;

@Controller
public class TypeOfExpenseController {
	
	@Autowired
	private TypeOfExpenseRepository typeOfExpenseRepository;
	
	// This function prints all expense types on the typeofexpenselist page. It also prints an empty form for adding a new expense type.
	@RequestMapping("/typeofexpenselist")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String typeOfExpenses(Model model) {
		model.addAttribute("typeOfExpenses", typeOfExpenseRepository.findAll());
		model.addAttribute("typeOfExpense", new TypeOfExpense());
		return "typeofexpenselist";
	}
	
	// This function prints existing expense type information, that can then be edited.
	@RequestMapping(value="/edit_type/{id}", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editExpType(@PathVariable(value="id") Long typeId, Model model) {
		model.addAttribute("exptype", typeOfExpenseRepository.findById(typeId));
		return "edittype";
	}
	
	// This function saves the added/edited expense type.
	@RequestMapping(value="/savetype", method=RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveExpType(TypeOfExpense typeOfExpense) {
		typeOfExpenseRepository.save(typeOfExpense);
		return "redirect:typeofexpenselist";
	}
	
	// This function deletes an expense type.
	@RequestMapping(value="/delete_type/{id}", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteExpType(@PathVariable(value="id") Long expTypeId) {
		typeOfExpenseRepository.deleteById(expTypeId);
		return "redirect:../typeofexpenselist";
	}
	

}
