/**
 * 
 */
package com.useful.person.core.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.useful.person.core.domain.Suggest;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.services.impl.SuggestServiceImpl;

/**
 * @author peter
 *
 */
@RestController
@RequestMapping("/suggest")
public class SuggestController {

	@Autowired
	private SuggestServiceImpl suggestService;

	@PostMapping
	public Suggest saveSuggest(@RequestBody Suggest suggest, Authentication user) {
		if (!StringUtils.isEmpty(user)) {
			UserInfo currentUser = (UserInfo) user.getPrincipal();
			suggest.setUser(currentUser);
		}
		return suggestService.saveOne(suggest);
	}
}
