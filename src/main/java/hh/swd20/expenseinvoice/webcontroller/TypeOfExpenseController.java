package hh.swd20.expenseinvoice.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.swd20.expenseinvoice.domain.TypeOfExpenseRepository;

@Controller
public class TypeOfExpenseController {
	
	@Autowired
	private TypeOfExpenseRepository typeOfExpenseRepository;
	
	@RequestMapping("/typeofexpenselist")
	public String typeOfExpenses(Model model) {
		model.addAttribute("typeOfExpenses", typeOfExpenseRepository.findAll());
		return "typeofexpenselist";
	}
	

}
