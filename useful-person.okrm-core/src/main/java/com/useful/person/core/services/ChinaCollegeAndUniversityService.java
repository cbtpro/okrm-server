/**
 * 
 */
package com.useful.person.core.services;

import java.util.List;

import com.useful.person.core.vo.ChinaCollegeAndUniversityLocationVO;

/**
 * @author peter
 *
 */
public interface ChinaCollegeAndUniversityService {

    List<ChinaCollegeAndUniversityLocationVO> findAllLocation();
}
