package com.demo.imdb.service;

import java.util.List;

import com.demo.imdb.model.IS;

public interface ImdbService {
	IS createImdb(IS is);

	IS updateImdb(IS is);

	List<IS> getAllImdb();

	IS getIsByImdbId(long isId);

	List<IS> findByLs(String ls);

	void deleteIS(long id);
	
}
