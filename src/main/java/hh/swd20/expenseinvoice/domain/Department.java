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
public class Department {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Size(min=2, max=12, message="Department name needs to be at least 2 characters and max 12 characters.")
	private String depName;
	
	@JsonIgnoreProperties("department")
	@OneToMany(cascade=CascadeType.ALL, mappedBy="department")
	private List<User> users;
	
	
	public Department() {
		
	}
	
	public Department(String depName) {
		super();
		this.depName = depName;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", depName=" + depName + "]";
	}
	
	
}
