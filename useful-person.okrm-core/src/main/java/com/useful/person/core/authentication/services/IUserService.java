package com.useful.person.core.authentication.services;

import java.util.List;

import com.useful.person.core.authentication.domain.SysUser;

/**
 * @author peter
 *
 */
public interface IUserService {

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	SysUser register(SysUser user);

	/**
	 * 删除用户
	 * @param user
	 * @return boolean
	 */
	boolean delete(SysUser user);
	
	/**
	 * 根据uuid查找用户
	 * @param uuid
	 * @return UserInfo
	 */
	SysUser findByUuid(String uuid);
	
	/**
	 * 判断用户是否存在
	 * @param username
	 * @return boolean
	 */
	boolean isExistUsername(String username);
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	SysUser findByUsername(String username);

	/**
	 * 根据昵称模糊查找用户
	 * @param nickname
	 * @return
	 */
	List<SysUser> findByNickname(String nickname);

	/**
	 * 根据手机号查找用户
	 * @param mobile
	 * @return
	 */
	SysUser findByMobile(String mobile);

}
