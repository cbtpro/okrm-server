package com.useful.person.repository;
import java.math.BigInteger;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author peter
 *
 */
@Transactional(rollbackOn = Exception.class)
@Repository
public class CommonDao {
	
	@PersistenceContext
	EntityManager entityManager;
	

	/**
	 * 获取记录条数
	 * @param sql
	 * @param params
	 * @return
	 */
	public Integer getCountBy(String sql,Map<String, Object> params){
		Query query =  entityManager.createNativeQuery(sql);
		if (params != null) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		BigInteger bigInteger  = (BigInteger) query.getSingleResult();
		return bigInteger.intValue();
	}
	
	/**
	 * 新增或者删除
	 * @param sql
	 * @param params
	 * @return
	 */
	public Integer deleteOrUpDate(String sql,Map<String, Object> params){
		Query query =  entityManager.createNativeQuery(sql);
		if (params != null) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.executeUpdate();
	}
}