/**
 * 省份地区
 * @see <a href="http://www.mca.gov.cn/article/sj/xzqh/2019/2019/201912251506.html">2019年11月中华人民共和国县以上行政区划代码</a>
 */
package com.useful.person.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.ProvinceArea;

/**
 * @author cbtpro
 *
 */
public interface ProvinceAreaRepository extends JpaRepository<ProvinceArea, String> {

    /**
     * 查询所有省份地区里列表
     * 
     * @return 省份地区列表
     */
    List<ProvinceArea> findAllByOrderByCode();

    /**
     * 根据code查询省份
     * 
     * @param code
     * @return 省份地区
     */
    ProvinceArea findByCodeOrderByCode(String code);

    /**
     * 根据上层code查询下层地区
     * 
     * @param upperCode
     * @return 省份地区列表
     */
    List<ProvinceArea> findByUpperCodeOrderByCode(String upperCode);
}
