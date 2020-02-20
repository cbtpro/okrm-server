/**
 * 
 */
package com.useful.person.core.services;

import java.util.List;

import com.useful.person.core.domain.Country;

/**
 * @author cbtpro
 *
 */
public interface CountryService {

	/**
	 * 保存国家/地区
	 * @param country 国家/地区
	 * @return 保存成功后的国家/地区
	 */
	Country save(Country country);

	/**
	 * 保存多个国家/地区
	 * @param countrys
	 * @return 保存成功后的国家/地区列表
	 */
	List<Country> saveAll(List<Country> countrys);

	/**
	 * 查询所有国家/地区
	 * @return 国家/地区列表
	 */
	List<Country> findAll();
}
