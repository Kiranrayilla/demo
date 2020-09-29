package com.demo.is.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.is.exception.ResourceNotFoundException;
import com.demo.is.model.IS;
import com.demo.is.repository.ISRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Service
@Transactional
@SecurityRequirement(name = "bearer")
public class ISServiceImpl implements ISService {

	@Autowired
	private ISRepository isRepo;

	@Override
	public IS createIS(IS is) {
		return isRepo.save(is);
	}

	@Override
	public IS updateIS(IS is) {
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
	public List<IS> getAllIS() {
		return this.isRepo.findAll();
	}

	@Override
	public IS getISById(long isId) {
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
