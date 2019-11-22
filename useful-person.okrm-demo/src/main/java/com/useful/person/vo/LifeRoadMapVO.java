/**
 * 
 */
package com.useful.person.vo;

import java.util.List;

import com.useful.person.domain.LifeRoadMap;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
@Builder
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
