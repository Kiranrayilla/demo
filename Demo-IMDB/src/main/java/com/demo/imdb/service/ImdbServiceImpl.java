package com.demo.imdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.imdb.exception.ResourceNotFoundException;
import com.demo.imdb.model.IS;
import com.demo.imdb.repository.ImdbRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Service
@Transactional
@SecurityRequirement(name = "bearer")
public class ImdbServiceImpl implements ImdbService {

	@Autowired
	private ImdbRepository isRepo;

	@Override
	public IS createImdb(IS is) {
		return isRepo.save(is);
	}

	@Override
	public IS updateImdb(IS is) {
		Optional<IS> isDb = this.isRepo.findById(is.getId());
		if (isDb.isPresent()) {
			IS isUpdate = isDb.get();
			isUpdate.setId(is.getId());
			isUpdate.setLs(is.getLs());
			isUpdate.setMsisdn(is.getMsisdn());
			isRepo.save(isUpdate);
			return isUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + is.getId());
		}
	}

	@Override
	public List<IS> getAllImdb() {
		return this.isRepo.findAll();
	}

	@Override
	public IS getIsByImdbId(long isId) {
		Optional<IS> isDb = this.isRepo.findById(isId);
		if (isDb.isPresent()) {
			return isDb.get();
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + isId);
		}
	}

	@Override
	public void deleteIS(long isId) {
		Optional<IS> isDb = this.isRepo.findById(isId);

		if (isDb.isPresent()) {
			this.isRepo.delete(isDb.get());
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + isId);
		}
	}

	@Override
	public List<IS> findByLs(String ls) {
		Optional<List<IS>> isDb = Optional.ofNullable(this.isRepo.findByLs(ls));
		if (isDb.isPresent()) {
			return isDb.get();
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + ls);
		}
	}
}
