package com.bedrosians.bedlogic.dao;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import com.bedrosians.bedlogic.util.PatternMatchMode;
import com.bedrosians.bedlogic.util.RestrictionOperation;

public interface GenericDao <T, PK extends Serializable>{
	
	T findById(PK id);
	List<T> findAll();
	List<T> findByParameter(String parameterName, String value);
	List<T> findByParameter(String parameterName, Long value);
	List<T> findByParameter(String parameterName, String value, RestrictionOperation op);
	List<T> findByParameterPattern(String parameterName, String value, PatternMatchMode matchMode);
	List<T> findByParameters(MultivaluedMap<String, String> queryParams);
	PK save(T newInstance);
	void update(T transientObject);
	void delete(T persistentObject);

}
