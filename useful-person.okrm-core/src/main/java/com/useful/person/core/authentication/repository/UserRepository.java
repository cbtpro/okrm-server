package com.useful.person.core.authentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.UserInfo;

/**
 * 
 * @author peter
 *
 */
public interface UserRepository extends JpaRepository<UserInfo, String> {

    /**
     * 根据用户名查询用户
     * 
     * @param username
     * @return 用户
     */
    UserInfo findByUsername(String username);

    /**
     * 根据手机号查询用户
     * 
     * @param mobile
     * @return 用户
     */
    UserInfo findByMobile(String mobile);

    /**
     * 查询非当前用户是否使用了这个手机号
     * 
     * @param uuid
     * @param mobile
     * @return
     */
    UserInfo findByUuidNotAndMobile(String uuid, String mobile);

    /**
     * 根据昵称模糊查询用户
     * 
     * @param nickname
     * @return 用户列表
     */
    List<UserInfo> findByNickname(String nickname);

    /**
     * 根据昵称模糊查询用户
     * 
     * @param nickname
     * @return 用户列表
     */
    List<UserInfo> findByNicknameLike(String nickname);

    /**
     * 根据用户名模糊查询用户
     * 
     * @param username
     * @return 用户列表
     */
    List<UserInfo> findByUsernameLike(String username);

    /**
     * 根据邮箱查询用户
     * 
     * @param email
     * @return 用户信息
     */
    UserInfo findByEmail(String email);

    /**
     * 根据邮箱地址查询除当前用户外的其他用户
     * 
     * @param uuid
     * @param email
     * @return UserInfo 用户信息
     */
    UserInfo findByUuidNotAndEmail(String uuid, String email);

    /**
     * 根据uuid和mobile查询用户
     * 
     * @param uuid
     * @param mobile
     * @return UserInfo 用户信息
     */
    UserInfo findByUuidAndMobile(String uuid, String mobile);
}
