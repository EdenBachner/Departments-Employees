package com.jbc.model.sequence;

import javax.validation.constraints.Min;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * This {@code Entity} used for saving the last Department ID's number in the system
 * @author eden_bachner
 *
 */

@Document(value = "departmentsSequence")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DepartmentSequence {

	/* attributes */
	@Id
	@EqualsAndHashCode.Include
	private int id;

	@Min(1)
	private long sequence;

	/* constructors */
	public DepartmentSequence(long sequence) {
		this.sequence = sequence;
	}

}
