/**
 * 
 */
package com.useful.person.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.useful.person.core.authentication.exception.GeneralException;
import com.useful.person.core.authentication.exception.UserNotExistException;
import com.useful.person.core.constants.UserAction;
import com.useful.person.core.domain.Role;
import com.useful.person.core.domain.UserInfo;
import com.useful.person.core.domain.UserInfoLog;
import com.useful.person.core.repository.RoleRepository;
import com.useful.person.core.repository.UserInfoLogRepository;
import com.useful.person.core.repository.UserInfoRepository;
import com.useful.person.core.services.RoleService;
import com.useful.person.core.vo.RoleRequestVO;

/**
 * @author peter
 *
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserInfoLogRepository userInfoLogRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Role saveRole(String uuid, Role role) {
        UserInfo currentUser = userInfoRepository.findById(uuid).orElseThrow(() -> new UserNotExistException(uuid));
        Role newRole = roleRepository.save(role);
        UserInfoLog userInfoLog = UserInfoLog.builder().user(currentUser).actionType(UserAction.ADD_ROLE)
                .actionValue(role.getRolename()).build();
        userInfoLogRepository.save(userInfoLog);
        return newRole;
    }

    @Override
    @Transactional
    public Role updateRole(String uuid, Role role) {
        UserInfo currentUser = userInfoRepository.findById(uuid).orElseThrow(() -> new UserNotExistException(uuid));
        Role newRole = roleRepository.save(role);
        UserInfoLog userInfoLog = UserInfoLog.builder().user(currentUser).actionType(UserAction.UPDATE_ROLE)
                .actionValue(role.getUuid()).build();
        userInfoLogRepository.save(userInfoLog);
        return newRole;
    }

    @Override
    @Transactional
    public void delRole(String uuid, String roleUuid) {
        UserInfo currentUser = userInfoRepository.findById(uuid).orElseThrow(() -> new UserNotExistException(uuid));
        roleRepository.deleteById(roleUuid);
        ;
        UserInfoLog userInfoLog = UserInfoLog.builder().user(currentUser).actionType(UserAction.DEL_ROLE)
                .oldValue(roleUuid).actionValue(null).build();
        userInfoLogRepository.save(userInfoLog);
    }

    @Override
    public Role findByUuid(String uuid) {
        return roleRepository.findById(uuid).orElseThrow(() -> new GeneralException(uuid, "角色uuid不存在！"));
    }

    @Override
    public List<Role> findAll(RoleRequestVO request) {
        String sortField = request.getSortField();
        String order = request.getSortOrder();
        Sort sort = Sort.by("ascend".equalsIgnoreCase(order) ? Direction.ASC : Direction.DESC,
                StringUtils.isBlank(sortField) ? "uuid" : sortField);
        return roleRepository.findAll(sort);
    }

    @Override
    public Role findByRolename(String rolename) {
        return roleRepository.findByRolename(rolename);
    }

    @Override
    public List<Role> findByRolenames(String[] rolenames) {
        Specification<Role> spec = new Specification<>() {

            private static final long serialVersionUID = 1L;

            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (rolenames != null && rolenames.length > 0) {
                    In<String> in = criteriaBuilder.in(root.get("rolename"));
                    for (String rolename : rolenames) {
                        in.value(rolename);
                    }
                    predicates.add(in);
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
        };
        return roleRepository.findAll(spec);
    }
}
