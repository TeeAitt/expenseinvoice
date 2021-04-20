package hh.swd20.expenseinvoice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import hh.swd20.expenseinvoice.webcontroller.DepartmentController;
import hh.swd20.expenseinvoice.webcontroller.ExpenseController;
import hh.swd20.expenseinvoice.webcontroller.TypeOfExpenseController;
import hh.swd20.expenseinvoice.webcontroller.UserController;
import hh.swd20.expenseinvoice.webcontroller.VatController;

/**
*
* This is a "smoke testing", with which it can be tested that major functions of the software are working correctly.
* In this the test is that the context is creating controllers (book, category and user).
*
*/

@ExtendWith(SpringExtension.class)  // This tells JUnit to use Spring's testing support.
@SpringBootTest			// With this regular tests and some more special tests can be used the test different parts of the application (in this case, the Expense Invoice application).
class ExpenseinvoiceApplicationTests {
	
	@Autowired
	private DepartmentController departmentController;
	
	@Autowired
	private ExpenseController expenseController;
	
	@Autowired
	private TypeOfExpenseController typeOfExpenseController;
	
	@Autowired
	private UserController userController;
	
	@Autowired
	private VatController vatController;

	@Test
	void deptContextLoads() {
		assertThat(departmentController).isNotNull();
	}
	
	@Test
	void expenseContextLoads() {
		assertThat(expenseController).isNotNull();
	}
	
	@Test
	void typeContextLoads() {
		assertThat(typeOfExpenseController).isNotNull();
	}
	
	@Test
	void userContextLoads() {
		assertThat(userController).isNotNull();
	}
	
	@Test
	void vatContextLoads() {
		assertThat(vatController).isNotNull();
	}

}
