package hh.swd20.expenseinvoice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.expenseinvoice.domain.Department;
import hh.swd20.expenseinvoice.domain.DepartmentRepository;
import hh.swd20.expenseinvoice.domain.Expense;
import hh.swd20.expenseinvoice.domain.ExpenseRepository;
import hh.swd20.expenseinvoice.domain.TypeOfExpense;
import hh.swd20.expenseinvoice.domain.TypeOfExpenseRepository;
import hh.swd20.expenseinvoice.domain.User;
import hh.swd20.expenseinvoice.domain.UserRepository;
import hh.swd20.expenseinvoice.domain.Vat;
import hh.swd20.expenseinvoice.domain.VatRepository;

@SpringBootApplication
public class ExpenseinvoiceApplication {
	private static final Logger log = LoggerFactory.getLogger(ExpenseinvoiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ExpenseinvoiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(ExpenseRepository expenseRepository, TypeOfExpenseRepository typeOfExpenseRepository, VatRepository vatRepository, UserRepository userRepository, DepartmentRepository departmentRepository) {
		return (args) -> {
			log.info("Save some expenses for demo purposes");
			
			// Demo expense types
			TypeOfExpense typeOfExpense1 = new TypeOfExpense("Office");
			TypeOfExpense typeOfExpense2 = new TypeOfExpense("Taxi");
			
			typeOfExpenseRepository.save(typeOfExpense1);
			typeOfExpenseRepository.save(typeOfExpense2);
			
			// Demo vat percentages
			Vat vat1 = new Vat(10.0);
			Vat vat2 = new Vat(14.0);
			Vat vat3 = new Vat(24.0);
			
			vatRepository.save(vat1);
			vatRepository.save(vat2);
			vatRepository.save(vat3);
			
			// Demo Departments
			Department dept1 = new Department("Finance");
			Department dept2 = new Department("Sales");
			Department dept3 = new Department("IT");
			
			departmentRepository.save(dept1);
			departmentRepository.save(dept2);
			departmentRepository.save(dept3);
			
			// Demo users
			User admin = new User("admin", "$2y$10$zZQ5SdDof5NOVL6e6EO3weqf9mmTEI/Z3QJVJ84mt7FkUtMyn.RyW", "admin_user", "admin_user", "admin@admin", "ADMIN", dept3);
			User john = new User("john", "$2y$10$tzLPbyrBchc9BjJQLsw/nui7T489dB5coP4XZPJMCjhp2T3VlOJWa", "John", "Johnson", "john@john.com", "ADMIN", dept3);
			User kelly = new User("kelly", "$2y$10$ro7II1dkvlHMU3VLyL8XQuYuP0An4.l6jKMMkX4j6RXii4ZvNia6e", "Kelly", "Smith", "kelly@kelly.com", "USER", dept2);
			User jack = new User("jack", "$2y$10$oAFiMIGyMJF52GsVgM1nie.ltyOqJjeocElFvBwWD.A5T3hbYRFwW", "Jack", "Jackson", "jack@jack.com", "USER", dept1);
			User matt = new User("matt", "$2y$10$JYt6PtGk9NfGcqHU.XSvPuoSfdfBbEkBZtwJzv0Usd1aILthifdYC", "Matt", "Mattson", "matt@matt.com", "USER", dept2);
			
			userRepository.save(admin);
			userRepository.save(john);
			userRepository.save(kelly);
			userRepository.save(jack);
			userRepository.save(matt);
			
			
			// Demo expenses with relationship entities
			expenseRepository.save(new Expense("1.4.2021", "Office supplies", 24.95, typeOfExpense1, vat3, jack));
			expenseRepository.save(new Expense("25.3.2021", "Taxi from customer to office", 37.60, typeOfExpense2, vat1, kelly));
			
			
			log.info("Fetch all expenses");
			for (Expense expense : expenseRepository.findAll()) {
				log.info(expense.toString());
			}
			
			log.info("Fetch all expense types");
			for (TypeOfExpense typeOfExpense : typeOfExpenseRepository.findAll()) {
				log.info(typeOfExpense.toString());
			}
			
			log.info("Fetch all vat percentages");
			for (Vat vat : vatRepository.findAll()) {
				log.info(vat.toString());
			}
			
			log.info("Fetch all users");
			for (User user : userRepository.findAll()) {
				log.info(user.toString());
			}
			
			log.info("Fetch all departments");
			for (Department department : departmentRepository.findAll()) {
				log.info(department.toString());
			}
		};
	}

}
