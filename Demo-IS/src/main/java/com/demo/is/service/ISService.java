package com.demo.is.service;

import java.util.List;

import com.demo.is.model.IS;

public interface ISService {
	IS createIS(IS is);

	IS updateIS(IS is);

	List<IS> getAllIS();

	IS getISById(long isId);

	List<IS> findByLs(String ls);

	void deleteIS(long id);
	
}
