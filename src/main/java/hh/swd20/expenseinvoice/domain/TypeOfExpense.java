package hh.swd20.expenseinvoice.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class TypeOfExpense {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Size(min=2, max=25, message="Type definition needs to be at least 2 characters and max 25 characters.")
	private String typeDef;
	
	// JsonIgnore will prevent an infinite loop that will happen with a JSON and OneToMany combination.
	@JsonIgnoreProperties("typeOfExpense")   // OneToMany annotation is the other end of the relationship annotations.
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "typeOfExpense")  // The "typeOfExpense" tells to which attribute this annotation will be linked, in other words: mapped.
	private List<Expense> expenses;   // The type is a list here, because TypeOfExpense can have many Expense items, in contrast to Expense that can only have one TypeOfExpense. 
	
	public TypeOfExpense() {
		
	}
	
	public TypeOfExpense(String typeDef) {
		super();
		this.typeDef = typeDef;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTypeDef() {
		return typeDef;
	}

	public void setTypeDef(String typeDef) {
		this.typeDef = typeDef;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	@Override
	public String toString() {
		return "TypeOfExpense [id=" + id + ", typeDef=" + typeDef + "]";
	}
	

}
