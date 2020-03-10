/**
 * 
 */
package com.useful.person.core.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.vo.Address;

/**
 * @author peter
 *
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

	/**
	 * 更新头像
	 * @param avatar
	 * @param uuid
	 */
	@Modifying
	@Query("update UserInfo u set u.avatar = ?1 where u.uuid = ?2")
	void updateAvatarImage(String avatar, String uuid);

	/**
	 * 更新用户名
	 * @param username
	 * @param uuid
	 */
	@Modifying
	@Query("update UserInfo u set u.username = ?1 where u.uuid = ?2")
	void updateUsername(String username, String uuid);

	/**
	 * 更新用户密码
	 * @param password
	 * @param uuid
	 */
	@Modifying
	@Query("update UserInfo u set u.password = ?1 where u.uuid = ?2")
	void updatePassword(String password, String uuid);

	/**
	 * 更新用户昵称
	 * @param nickname
	 * @param uuid
	 */
	@Modifying
	@Query("update UserInfo u set u.nickname = ?1 where u.uuid = ?2")
	void updateNickname(String nickname, String uuid);

	/**
	 * 更新用户手机号
	 * @param mobile
	 * @param uuid
	 */
	@Modifying
	@Query("update UserInfo u set u.mobile = ?1 where u.uuid = ?2")
	void updateMobile(String mobile, String uuid);

	@Modifying
	@Query("update UserInfo u set u.email = ?1 where u.uuid = ?2")
	void updateEmail(String email, String uuid);

	/**
	 * 更新用户生日
	 * @param birthday
	 * @param uuid
	 */
	@Modifying
	@Query("update UserInfo u set u.birthday = ?1 where u.uuid = ?2")
	void updateBirthday(Date birthday, String uuid);

	/**
	 * 查询用户地址信息
	 * @return Address 用户地址信息
	 */
	@Query("select new com.useful.person.core.vo.Address(u.longitude, u.latitude) from UserInfo u where u.uuid = :uuid")
	Address findByUuid(@Param("uuid") String uuid);

	@Modifying
	@Query("update UserInfo u set u.longitude = :longitude, u.latitude = :latitude where u.uuid = :uuid")
	int updateAddress(@Param("longitude") Double longitude, @Param("latitude") Double latitude, @Param("uuid") String uuid);
}
