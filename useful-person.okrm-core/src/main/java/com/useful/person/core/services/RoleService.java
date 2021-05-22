/**
 * 
 */
package com.useful.person.core.services;

import java.util.List;

import com.useful.person.core.domain.Role;
import com.useful.person.core.vo.RoleRequestVO;

/**
 * @author peter
 *
 */
public interface RoleService {

    /**
     * 查询所有角色
     * 
     * @return List<Role>
     */
    List<Role> findAll();

    List<Role> findAll(RoleRequestVO request);

    /**
     * 新增角色
     * 
     * @param uuid
     * @param role
     * @return Role
     */
    Role saveRole(String uuid, Role role);

    /**
     * 更新角色
     * 
     * @param uuid
     * @param role
     * @return Role
     */
    Role updateRole(String uuid, Role role);

    /**
     * 删除角色
     * 
     * @param uuid
     * @param role
     * @return 删除的记录数
     */
    void delRole(String uuid, String roleUuid);

    /**
     * 根据UUID查询角色
     * 
     * @param uuid
     * @return Role
     */
    Role findByUuid(String uuid);

    List<Role> findByRolenames(String[] rolenames);

    Role findByRolename(String rolename);
}
