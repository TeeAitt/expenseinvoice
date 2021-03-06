package hh.swd20.expenseinvoice.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
	
	List<Department> findByDepName(String depName);

}
