package hh.swd20.expenseinvoice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


			//Describes the structure of the database table that is corresponding to the class
@Entity		// table name, column names and data types, primary key etc. In short: an entity represents a table in relational database.
public class Expense {
	
	@Id		// Sets the primary key. It annotates an attribute to be the primary key. In this the attribute is named id (as well).
	@GeneratedValue(strategy=GenerationType.IDENTITY)  // Automatically generates new primary key values (in this it's the id), when new information is inputed in the table.
	private long id;
	private String date;
	private String expenseDef;
	private double sum;
	
	
	@JsonIgnoreProperties("expenses")
	@ManyToOne	// This annotation creates a link to another database table, and by that creates a relationship between the tables.
	@JoinColumn(name="expTypeId")	// This annotation defines the owner of the relationship. In this, it is the TypeOfExpense table and its primary key "id".
	private TypeOfExpense typeOfExpense;	// The type of this attribute is TypeOfExpense, because of the TypeOfExpense object, which is the owner of this relationship.
	
	public Expense() {
		
	}
	
	public Expense(String date, String expenseDef, double sum, TypeOfExpense typeOfExpense) {
		super();
		this.date = date;
		this.expenseDef = expenseDef;
		this.sum = sum;
		this.typeOfExpense = typeOfExpense;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getExpenseDef() {
		return expenseDef;
	}

	public void setExpenseDef(String expenseDef) {
		this.expenseDef = expenseDef;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
	
	

	public TypeOfExpense getTypeOfExpense() {
		return typeOfExpense;
	}

	public void setTypeOfExpense(TypeOfExpense typeOfExpense) {
		this.typeOfExpense = typeOfExpense;
	}

	@Override
	public String toString() {
		if (this.typeOfExpense != null)
		return "Expense [id=" + id + ", date=" + date + ", expenseDef=" + expenseDef + ", sum=" + sum
				+ ", typeOfExpense=" + this.getTypeOfExpense() + "]";
		else
		return "Expense [id=" + id + ", date=" + date + ", expenseDef=" + expenseDef + ", sum=" + sum
				+ "]";
	}


}
