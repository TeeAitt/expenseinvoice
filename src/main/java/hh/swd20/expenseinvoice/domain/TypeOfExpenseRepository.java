package hh.swd20.expenseinvoice.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TypeOfExpenseRepository extends CrudRepository<TypeOfExpense, Long> {
	
	List<TypeOfExpense> findByTypeDef(String typeDef);

}
