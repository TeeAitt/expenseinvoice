package hh.swd20.expenseinvoice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

@ExtendWith(SpringExtension.class)  // This tells JUnit to use Spring's testing support.
@DataJpaTest
public class RepositoryTest {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private TypeOfExpenseRepository typeOfExpenseRepository;
	
	@Autowired
	 private UserRepository userRepository;
	
	@Autowired
	private VatRepository vatRepository;
	
	@Test
	public void finByDepNameShoudReturnDepartment() {
		List<Department> departments = departmentRepository.findByDepName("IT");
		assertThat(departments).hasSize(1);
		assertThat(departments.get(0).getDepName()).isEqualTo("IT");
	}
	
	@Test
	public void findByDateShoudReturnDefinition() {
		List<Expense> expenses = expenseRepository.findByDate("1.4.2021");
		assertThat(expenses).hasSize(1);
		assertThat(expenses.get(0).getExpenseDef()).isEqualTo("Office supplies");
	}
	
	@Test
	public void findByTypeDefShoudReturnType() {
		List<TypeOfExpense> typeOfExpenses = typeOfExpenseRepository.findByTypeDef("Taxi");
		assertThat(typeOfExpenses).hasSize(1);
		assertThat(typeOfExpenses.get(0).getTypeDef()).isEqualTo("Taxi");
	}
	
	@Test
	public void findByUsernameShouldReturnUser() {
		User users = userRepository.findByUsername("admin");
		assertThat(users.getEmail()).isEqualTo("admin@admin.com");
	}
	
	@Test
	public void findByVatPercentageShouldReturnPercentage() {
		List<Vat> vats = vatRepository.findByVatPercentage(14.0);
		assertThat(vats).hasSize(1);
		assertThat(vats.get(0).getVatPercentage()).isEqualTo(14.0);
	}
	
	@Test
	public void addNewDepartment() {
		Department department = new Department("Marketing");
		departmentRepository.save(department);
		assertThat(department.getId()).isNotNull();
	}
	
	@Test
	public void addNewExpense() {
		Expense expense = new Expense("15.4.2021", "Coffee to office", 15.79, typeOfExpenseRepository.findByTypeDef("Coffee supplies").get(0), vatRepository.findByVatPercentage(14.0).get(0), userRepository.findByUsername("jack"));
		expenseRepository.save(expense);
		assertThat(expense.getId()).isNotNull();
	}
	
	@Test
	public void addNewExpType() {
		TypeOfExpense type = new TypeOfExpense("Phone & Data costs");
		typeOfExpenseRepository.save(type);
		assertThat(type.getId()).isNotNull();
	}
	
	@Test
	public void addNewUser() {
		User user = new User("will","$2y$10$5qPVwsVD6N2ADHAgojojj.H4RYf8VYUaRx6Z8qnam6g.EnIN9OC6G", "Will", "Wilson", "will@will.com", "USER", departmentRepository.findByDepName("Sales").get(0));
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();		
	}
	
	@Test
	public void addNewVat() {
		Vat vat = new Vat(12.0);
		vatRepository.save(vat);
		assertThat(vat.getId()).isNotNull();
	}

}
