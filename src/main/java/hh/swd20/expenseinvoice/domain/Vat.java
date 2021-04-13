package hh.swd20.expenseinvoice.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Vat {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private double vatPercentage;
	
	
	@JsonIgnoreProperties("vat")
	@OneToMany(cascade = CascadeType.ALL, mappedBy="vat")
	private List<Expense> expenses;
	
	public Vat() {
		
	}
	
	public Vat(double vatPercentage) {
		super();
		this.vatPercentage = vatPercentage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getVatPercentage() {
		return vatPercentage;
	}

	public void setVatPercentage(double vatPercentage) {
		this.vatPercentage = vatPercentage;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	@Override
	public String toString() {
		return "Vat [id=" + id + ", vatPercentage=" + vatPercentage + "]";
	}
	
	

}
