package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.NoArgsConstructor;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,property = "@id",scope = SUSDetail.class)
@NoArgsConstructor
public class SUSDetail {
    private String ls;
    private String msisdn;
	public String getLs() {
		return ls;
	}
	public void setLs(String ls) {
		this.ls = ls;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	@Override
	public String toString() {
		return "SUSDetail [ls=" + ls + ", msisdn=" + msisdn + "]";
	}
}
