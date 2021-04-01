package hh.swd20.expenseinvoice.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.swd20.expenseinvoice.domain.ExpenseRepository;

@Controller
public class ExpenseController {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@RequestMapping("/expenseslist")
	public String expensesList(Model model) {
		model.addAttribute("expenses", expenseRepository.findAll());
		return "expenseslist";
	}
	

}
