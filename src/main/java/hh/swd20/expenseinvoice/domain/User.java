package hh.swd20.expenseinvoice.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, updatable=false)
	private long id;
	
	// The username cannot be empty and it needs to be unique (so, two same user name is not possible in the database).
	@Column(name="username", nullable=false, unique=true)
	private String username;
	
	@Column(name="passwordHash", nullable=false)
	private String passwordHash;
	
	@Column(name="firstname", nullable=false)
	private String firstName;
	
	@Column(name="lastname", nullable=false)
	private String lastName;
	
	@Column(name="email", nullable=false, unique=true)
	private String email;
	
	@Column(name="role", nullable=false)
	private String role;
	
	@JsonIgnoreProperties("user")
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Expense> expenses;
	
	@JsonIgnoreProperties("users")
	@ManyToOne
	@JoinColumn(name="depId")
	private Department department;
	
	public User() {
		
	}
	
	public User(String username, String passwordHash, String firstName, String lastName, String email, String role, Department department) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.department = department;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		if (this.department != null)
		return "User [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", role=" + role + ", role=" + this.getDepartment() + "]";
		else
			return "User [id=" + id + ", username=" + username + ", passwordHash=" + passwordHash + ", firstName="
			+ firstName + ", lastName=" + lastName + ", email=" + email + ", role=" + role + "]";	
	}
	
	

}
