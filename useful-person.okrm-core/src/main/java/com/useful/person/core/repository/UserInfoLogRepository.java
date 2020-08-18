/**
 * 
 */
package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.UserInfoLog;

/**
 * @author peter
 *
 */
public interface UserInfoLogRepository extends JpaRepository<UserInfoLog, String> {

}
