/**
 * 
 */
package com.useful.person.core.repository;

import java.util.Date;
import java.util.List;

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
	 * 
	 * @param avatar
	 * @param uuid
	 */
	@Modifying
	@Query("update UserInfo u set u.avatar = ?1 where u.uuid = ?2")
	void updateAvatarImage(String avatar, String uuid);

	/**
	 * 更新用户名
	 * 
	 * @param username
	 * @param uuid
	 */
	@Modifying
	@Query("update UserInfo u set u.username = ?1 where u.uuid = ?2")
	void updateUsername(String username, String uuid);

	/**
	 * 更新用户密码
	 * 
	 * @param password
	 * @param uuid
	 */
	@Modifying
	@Query("update UserInfo u set u.password = ?1 where u.uuid = ?2")
	void updatePassword(String password, String uuid);

	/**
	 * 更新用户昵称
	 * 
	 * @param nickname
	 * @param uuid
	 */
	@Modifying
	@Query("update UserInfo u set u.nickname = ?1 where u.uuid = ?2")
	void updateNickname(String nickname, String uuid);

	/**
	 * 更新用户手机号
	 * 
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
	 * 
	 * @param birthday
	 * @param uuid
	 */
	@Modifying
	@Query("update UserInfo u set u.birthday = ?1 where u.uuid = ?2")
	void updateBirthday(Date birthday, String uuid);

	/**
	 * 查询用户地址信息
	 * 
	 * @return Address 用户地址信息
	 */
	@Query("select new com.useful.person.core.vo.Address(u.uuid, u.nickname, u.avatar, u.longitude, u.latitude) from UserInfo u where u.uuid = :uuid")
	Address findAddressByUuid(@Param("uuid") String uuid);

	@Modifying
	@Query("update UserInfo u set u.longitude = :longitude, u.latitude = :latitude where u.uuid = :uuid")
	int updateAddress(@Param("longitude") Double longitude, @Param("latitude") Double latitude,
			@Param("uuid") String uuid);

	/**
	 * 经度趋势是170 -> 180/ (-180) -> (-170)样式的。
	 * 
	 * @param range0MinLongitude 第一个区间范围的最小经度
	 * @param range0MaxLongitude 第一个区间范围的最大经度
	 * @param range1MinLongitude 第二个区间范围的最小经度
	 * @param range1MaxLongitude 第二个区间范围的最大经度
	 * @param minLatitude 最小纬度
	 * @param maxLatitude 最大纬度
	 * @return List<Address> 附近人的经纬度信息集合
	 */
	@Query("select new com.useful.person.core.vo.Address(u.uuid, u.nickname, u.avatar, u.longitude, u.latitude) from UserInfo u "
			+ "where (u.longitude between :range0MinLongitude and :range0MaxLongitude or u.longitude between :range1MinLongitude and :range1MaxLongitude) "
			+ "and u.latitude between :minLatitude and :maxLatitude")
	List<Address> queryUserNearByUserAddressRange(Double range0MinLongitude, Double range0MaxLongitude,
			Double range1MinLongitude, Double range1MaxLongitude, Double minLatitude, Double maxLatitude);

	/**
	 * @param minLongitude 范围的最小经度
	 * @param maxLongitude 范围的最小纬度
	 * @param minLatitude  范围的最大经度
	 * @param maxLatitude  范围的最大纬度
	 * @return List<Address> 附近的人经纬度信息集合
	 */
	@Query("select new com.useful.person.core.vo.Address(u.uuid, u.nickname, u.avatar, u.longitude, u.latitude) from UserInfo u where u.longitude between :minLongitude and :maxLongitude and u.latitude between :minLatitude and :maxLatitude")
	List<Address> queryUserNearbyUserAddress(Double minLongitude, Double maxLongitude, Double minLatitude, Double maxLatitude);
}
