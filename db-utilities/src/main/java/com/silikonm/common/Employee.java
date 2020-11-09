package com.silikonm.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "employee", catalog = "employeedata", uniqueConstraints = { @UniqueConstraint(columnNames = "employee_id") })
public class Employee implements Serializable {

	private int employeeId; // `employee`.`employee_id`,
	private String firstName; // `employee`.`first_name`,
	private String lastName; // `employee`.`last_name`,
	private String address; // `employee`.`address`

	public Employee() {

	}

	public Employee(int employeeId, String firstName, String lastName,
			String address) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}

	@Id
	@Column(name = "employee_id", unique = true, nullable = false)
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
