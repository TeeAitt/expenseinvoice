package hh.swd20.expenseinvoice.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.expenseinvoice.domain.Expense;
import hh.swd20.expenseinvoice.domain.ExpenseRepository;
import hh.swd20.expenseinvoice.domain.TypeOfExpenseRepository;
import hh.swd20.expenseinvoice.domain.VatRepository;

@Controller
public class ExpenseController {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private TypeOfExpenseRepository typeOfExpenseRepository;
	
	@Autowired
	private VatRepository vatRepository;
	
	// This function is for login page.
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	// This function prints all expenses on the expenseslist page.
	@RequestMapping("/expenseslist")
	public String expensesList(Model model) {
		model.addAttribute("expenses", expenseRepository.findAll());
		return "expenseslist";
	}
	
	// This function prints an empty form for adding a new expense.
	@RequestMapping(value="/add")
	public String addExpense(Model model) {
		model.addAttribute("expense", new Expense());
		model.addAttribute("typeOfExpenses", typeOfExpenseRepository.findAll());
		model.addAttribute("vats", vatRepository.findAll());
		return "addexpense";
	}
	
	// This function saves the added expense.
	@RequestMapping(value="/saveexp", method=RequestMethod.POST)
	public String saveExpense(Expense expense) {
		expenseRepository.save(expense);
		return "redirect:expenseslist";
	}
	
	// This function deletes an expense.
	@RequestMapping(value="/delete_exp/{id}", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")	// The PreAuthorize annotation prevents from unauthorized users the possibility to delete an expense by using "/delete/id" end point.
	public String deleteExpense(@PathVariable(value="id") Long expenseId) {
		expenseRepository.deleteById(expenseId);
		return "redirect:../expenseslist";
	}

}
