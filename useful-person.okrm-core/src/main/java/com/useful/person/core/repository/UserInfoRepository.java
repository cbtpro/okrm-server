/**
 * 
 */
package com.useful.person.core.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
	@Query("update UserInfo u set u.password = :password where u.uuid = :uuid")
	int updateUserPassword(String password, String uuid);

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
	void updateBirthday(Timestamp birthday, String uuid);

	/**
	 * 更新用户身份证信息
	 * @param identityCardName
	 * @param identityCardNo
	 * @param uuid
	 */
	@Modifying
	@Query("update UserInfo u set u.identityCardName = :identityCardName, u.identityCardNo = :identityCardNo where u.uuid = :uuid")
	void updateIdentityCard(String identityCardName, String identityCardNo, String uuid);

	@Modifying(clearAutomatically = true)
	@Query("update UserInfo u set u.avatar = :avatar, u.username = :username, u.nickname = :nickname, u.province = :province, u.city = :city, u.county = :county, u.birthday = :birthday where u.uuid = :uuid")
	int updateUserInfo(String uuid, String avatar, String username, String nickname, String province, String city, String county, Timestamp birthday);
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

	/**
	 * 根据条件分页查询所有用户
	 * @param spec
	 * @param pageable
	 * @return
	 */
	Page<UserInfo> findAll(Specification<UserInfo> spec, Pageable pageable);

	UserInfo findByUsername(String username);

	@Query("select u from UserInfo u where u.username in :usernames")
	List<UserInfo> findByUsernames(@Param("usernames") List<String> usernames);
}
