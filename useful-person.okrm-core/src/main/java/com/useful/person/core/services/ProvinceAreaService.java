/**
 * 
 */
package com.useful.person.core.services;

import java.util.List;

import com.useful.person.core.domain.ProvinceArea;

/**
 * @author cbtpro
 *
 */
public interface ProvinceAreaService {

    /**
     * 保存国家/地区
     * 
     * @param country 国家/地区
     * @return 保存成功后的国家/地区
     */
    ProvinceArea save(ProvinceArea provinceArea);

    /**
     * 保存多个国家/地区
     * 
     * @param countrys
     * @return 保存成功后的国家/地区列表
     */
    List<ProvinceArea> saveAll(List<ProvinceArea> provinceAreas);

    /**
     * 查询所有省份地区的合集
     * 
     * @return 省份地区列表
     */
    List<ProvinceArea> findAll();

    /**
     * 根据uuid查询省份信息
     * 
     * @param uuid
     * @return 省份信息
     */
    ProvinceArea findProvinceAreaByUuid(String uuid);

    /**
     * 根据查询省份地区
     * 
     * @param code
     * @return 省份地区信息
     */
    ProvinceArea findProvinceAreaByCode(String code);

    /**
     * 根据upperCode查询省份地区的子集
     * 
     * @param code
     * @return 省份地区列表
     */
    List<ProvinceArea> findChilds(String upperCode);

}
