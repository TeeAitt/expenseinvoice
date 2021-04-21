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

import hh.swd20.expenseinvoice.domain.Expense;
import hh.swd20.expenseinvoice.domain.ExpenseRepository;
import hh.swd20.expenseinvoice.domain.TypeOfExpenseRepository;
import hh.swd20.expenseinvoice.domain.UserRepository;
import hh.swd20.expenseinvoice.domain.VatRepository;

@Controller
public class ExpenseController {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private TypeOfExpenseRepository typeOfExpenseRepository;
	
	@Autowired
	private VatRepository vatRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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
		model.addAttribute("users", userRepository.findAll());
		return "addexpense";
	}
	
	// This function prints existing expense information, that can then be edited.
	@RequestMapping(value="/edit_exp/{id}", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editExpense(@PathVariable(value="id") Long expenseId, Model model) {
		model.addAttribute("expense", expenseRepository.findById(expenseId));
		model.addAttribute("typeOfExpenses", typeOfExpenseRepository.findAll());
		model.addAttribute("vats", vatRepository.findAll());
		model.addAttribute("users", userRepository.findAll());
		return "editexpense";
	}
	
	// This function saves the added/edited expense.
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
	
	
	/************************* RESTful services below ****************************
	 */
	
	// This REST function will get all the expenses.
	@RequestMapping(value="/expenses", method=RequestMethod.GET)
	public @ResponseBody List<Expense> expenseListRest() {
		return (List<Expense>) expenseRepository.findAll();
	}
	
	// This REST function will get an expense by its id.
	@RequestMapping(value="/expenses/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Expense> findExpenseRest(@PathVariable("id") Long expId) {
		return expenseRepository.findById(expId);
	}
	
	// This REST function will save a new expense.
	@RequestMapping(value="/expenses", method=RequestMethod.POST)
	public @ResponseBody Expense saveExpenseRest(@RequestBody Expense expense) {
		return expenseRepository.save(expense);
	}

}
