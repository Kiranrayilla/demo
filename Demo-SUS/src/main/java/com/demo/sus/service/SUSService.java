package com.demo.sus.service;

import java.util.List;

import com.demo.sus.model.SUS;
import com.demo.sus.model.SUSDetail;

public interface SUSService {
	SUS createSUS(SUS sus);

	SUS updateSUS(SUS sus);

	List<SUS> getAllSUS();

	SUS getSUSById(long id);

	void deleteSUSById(long id);

	List<SUS> findByLs(String ls);
	
	List<SUSDetail> findByLsFromImdb(String ls);

	String updateStatus(SUS sus);
	
}
