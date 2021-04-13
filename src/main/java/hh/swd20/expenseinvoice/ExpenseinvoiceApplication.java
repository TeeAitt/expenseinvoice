package hh.swd20.expenseinvoice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.expenseinvoice.domain.Expense;
import hh.swd20.expenseinvoice.domain.ExpenseRepository;
import hh.swd20.expenseinvoice.domain.TypeOfExpense;
import hh.swd20.expenseinvoice.domain.TypeOfExpenseRepository;

@SpringBootApplication
public class ExpenseinvoiceApplication {
	private static final Logger log = LoggerFactory.getLogger(ExpenseinvoiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ExpenseinvoiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(ExpenseRepository expenseRepository, TypeOfExpenseRepository typeOfExpenseRepository) {
		return (args) -> {
			log.info("Save some expenses for demo purposes");
			TypeOfExpense typeOfExpense1 = new TypeOfExpense("Office");
			TypeOfExpense typeOfExpense2 = new TypeOfExpense("Taxi");
			
			typeOfExpenseRepository.save(typeOfExpense1);
			typeOfExpenseRepository.save(typeOfExpense2);
			
			
			expenseRepository.save(new Expense("1.4.2021", "Office supplies", 24.95, typeOfExpense1));
			expenseRepository.save(new Expense("25.3.2021", "Taxi from customer to office", 37.60, typeOfExpense2));
			
			log.info("Fetch all expenses");
			for (Expense expense : expenseRepository.findAll()) {
				log.info(expense.toString());
			}
			
			log.info("Fetch all expenses");
			for (TypeOfExpense typeOfExpense : typeOfExpenseRepository.findAll()) {
				log.info(typeOfExpense.toString());
			}
		};
	}

}
