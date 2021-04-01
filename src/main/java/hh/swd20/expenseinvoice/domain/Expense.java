package hh.swd20.expenseinvoice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


			//Describes the structure of the database table that is corresponding to the class
@Entity		// table name, column names and data types, primary key etc. In short: an entity represents a table in relational database.
public class Expense {
	
	@Id		// Sets the primary key. It annotates an attribute to be the primary key. In this the attribute is named id (as well).
	@GeneratedValue(strategy=GenerationType.IDENTITY)  // Automatically generates new primary key values (in this it's the id), when new information is inputed in the table.
	private long id;
	private String date;
	private String expenseDef;
	private double sum;
	
	public Expense() {
		
	}
	
	public Expense(String date, String expenseDef, double sum) {
		super();
		this.date = date;
		this.expenseDef = expenseDef;
		this.sum = sum;
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

	@Override
	public String toString() {
		return "Expense [id=" + id + ", date=" + date + ", expenseDef=" + expenseDef + ", sum=" + sum + "]";
	}
	
	
	

}
