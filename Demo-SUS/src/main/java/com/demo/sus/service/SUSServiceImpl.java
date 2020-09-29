package com.demo.sus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.sus.exception.ResourceNotFoundException;
import com.demo.sus.model.SUS;
import com.demo.sus.model.SUSDetail;
import com.demo.sus.repository.SUSRepository;
import com.demo.sus.util.RestUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Service
@Transactional
@SecurityRequirement(name = "bearer")
public class SUSServiceImpl implements SUSService {

	@Autowired
	private SUSRepository susRepo;

	@Value("${imdb.host}")
	private String host;

	@Value("${imdb.port}")
	private String port;

	@Value("${imdb.resource}")
	private String resource;

	@Autowired
	private RestUtil restUtil;

	@Override
	public SUS createSUS(SUS sus) {
		return susRepo.save(sus);
	}

	@Override
	public SUS updateSUS(SUS sub) {
		Optional<SUS> subDb = this.susRepo.findById(sub.getId());

		if (subDb.isPresent()) {
			SUS susUpdate = subDb.get();
			susUpdate.setId(sub.getId());
			susUpdate.setLs(sub.getLs());
			susUpdate.setStatus(sub.getStatus());
			susRepo.save(susUpdate);
			return susUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + sub.getId());
		}
	}

	@Override
	public List<SUS> getAllSUS() {
		return this.susRepo.findAll();
	}

	@Override
	public SUS getSUSById(long susId) {

		Optional<SUS> susDb = this.susRepo.findById(susId);

		if (susDb.isPresent()) {
			return susDb.get();
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + susId);
		}
	}

	@Override
	public void deleteSUSById(long susId) {
		Optional<SUS> susDb = this.susRepo.findById(susId);

		if (susDb.isPresent()) {
			this.susRepo.delete(susDb.get());
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + susId);
		}

	}

	@Override
	public List<SUS> findByLs(String ls) {
		Optional<List<SUS>> isDb = Optional.ofNullable(this.susRepo.findByLs(ls));
		if (isDb.isPresent()) {
			return isDb.get();
		} 
		throw new ResourceNotFoundException("Record not found with id : " + ls);
	}

	@Override
	public List<SUSDetail> findByLsFromImdb(String ls) {

		List<SUSDetail> finalList = new ArrayList<SUSDetail>();
		List<SUS> isDb = this.susRepo.findByLs(ls);
		
		if (!isDb.isEmpty()) {
			for(SUS itr:isDb) {
				finalList.add(convertToDto(itr));
			}
			return finalList;
		} else {
			List<SUSDetail> susDetails = restUtil.fetchRestData(host, port, resource, ls);
			if(susDetails!=null) {
				return susDetails;
			}
			throw new ResourceNotFoundException("Record not found with id : " + ls);
		}
	}

	private SUSDetail convertToDto(SUS sus) {
		SUSDetail detail = new SUSDetail();
		detail.setId(sus.getId());
		detail.setLs(sus.getLs());
		detail.setMsisdn(sus.getMsisdn());
		detail.setCreatedAt(sus.getCreatedAt());
		detail.setUpdatedAt(sus.getUpdatedAt());

		return detail;
	}

	@Override
	public String updateStatus(SUS sus) {
		
		//Optional<List<SUS>> isDb = Optional.ofNullable(this.susRepo.findByLs(sus.getLs()));
		List<SUS> isDb = this.susRepo.findByLs(sus.getLs());
		if (!isDb.isEmpty()) {
			return "SUCCESS";
		} else {
			System.out.println("Host value is : " + host + port + resource + " ----");
			List<SUSDetail> isData = restUtil.fetchRestData(host, port, resource, sus.getLs());
			if (!isData.isEmpty()) {
				List<SUSDetail> is = isData;
				is.forEach(k -> {
					SUS updateSus = new SUS();
					updateSus.setLs(k.getLs());
					updateSus.setMsisdn(k.getMsisdn());
					updateSus.setStatus("SUCCESS");
					this.susRepo.save(updateSus);
				});
				return "SUCCESS";
			}
			else {
				
			}

		}
		return "FAILURE";
	}

}
