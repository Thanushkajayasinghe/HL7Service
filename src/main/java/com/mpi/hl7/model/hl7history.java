package com.mpi.hl7.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hl7history")
public class hl7history {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long serial;
	private String hl7full;
	private String cdate;
	private Integer cby;

	public hl7history() {

	}

	public hl7history(Long id, Long serial, String hl7full, String cdate, Integer cby) {
		super();
		this.id = id;
		this.serial = serial;
		this.hl7full = hl7full;
		this.cdate = cdate;
		this.cby = cby;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public Integer getCby() {
		return cby;
	}

	public void setCby(Integer cby) {
		this.cby = cby;
	}

}
