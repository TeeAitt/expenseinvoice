package hh.swd20.expenseinvoice.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

		//CrudRepository provides CRUD (create, read, update, delete) functions for the entity class, that is managed.
		// In this situation the entity class is the Book class.
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
	
	List<Expense> findByDate(String date);  // This method creates a findBuTitle query, which means that with it you can find (book) titles from the database (by making queries).

}
