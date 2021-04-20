package hh.swd20.expenseinvoice.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VatRepository extends CrudRepository<Vat, Long> {
	
	List<Vat> findByVatPercentage(double vatPercentage);

}
