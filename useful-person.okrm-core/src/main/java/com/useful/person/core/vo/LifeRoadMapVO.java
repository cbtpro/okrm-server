/**
 * 
 */
package com.useful.person.core.vo;

import java.util.List;

import com.useful.person.core.domain.LifeRoadMap;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author peter
 *
 */
@Builder
@ToString
public class LifeRoadMapVO {

	@Getter
	@Setter
	private String uuid;

	@Getter
	@Setter
	private String nickname;

	@Getter
	@Setter
	private List<LifeRoadMap> lifeRoadMaps;
}
