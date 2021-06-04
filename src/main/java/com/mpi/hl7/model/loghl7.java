package com.mpi.hl7.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "loghl7")
public class loghl7 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serial;
	private String hl7full;
	private String api;
	private String status;
	private String cdate;
	private String cby;

	public loghl7() {

	}

	public loghl7(Long serial, String hl7full, String api, String status, String cdate, String cby) {
		super();
		this.serial = serial;
		this.hl7full = hl7full;
		this.api = api;
		this.status = status;
		this.cdate = cdate;
		this.cby = cby;
	}

	public Long getSerial() {
		return serial;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
	}

	public String getHl7full() {
		return hl7full;
	}

	public void setHl7full(String hl7full) {
		this.hl7full = hl7full;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getCby() {
		return cby;
	}

	public void setCby(String cby) {
		this.cby = cby;
	}

}
