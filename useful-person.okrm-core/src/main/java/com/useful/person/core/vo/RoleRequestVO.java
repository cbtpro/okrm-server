/**
 * 
 */
package com.useful.person.core.vo;

import com.useful.person.core.domain.Role;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
public class RoleRequestVO extends Role {

	private static final long serialVersionUID = -9140421128873346454L;

	@Getter
	@Setter
	private int size = 10;

	@Getter
	@Setter
	private int page = 1;

	@Getter
	@Setter
	private String sortOrder;

	@Getter
	@Setter
	private String sortField;
}
