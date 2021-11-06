package com.jbc.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jbc.util.model.EmployeeProfessionUtil;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * {@code Employee} {@code Entity} that is using the {@link Department} Entity.
 * This {@code Entity} used for creating the Employees in the system.
 * 
 * @author eden_bachner
 *
 */
@Document(value = "employees")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

	/* attributes */
	@Id
	@EqualsAndHashCode.Include
	@ApiModelProperty(value = "Auto-incremented", example = "0")
	private long id;

	@NotNull
	@Size(min = 2)
	private String firstName;

	@NotNull
	@Size(min = 2)
	private String lastName;

	@NotNull
	private EmployeeProfessionUtil profession;

	@NotNull
	@ApiModelProperty(value = "Only as response")
	private String departmentName;

	/* constructors */
	public Employee(String firstName, String lastName, EmployeeProfessionUtil profession, String departmentName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.profession = profession;
		this.departmentName = departmentName;
		departmentName="";
	}

	public Employee(long id, String firstName, String lastName, EmployeeProfessionUtil profession) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profession = profession;
		departmentName="";
	}

}
