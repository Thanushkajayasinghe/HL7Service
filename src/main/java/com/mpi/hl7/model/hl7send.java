package com.mpi.hl7.model;

public class hl7send {

	private String hl7full;
	private String apikey;
	private String institutename;

	public hl7send() {

	}

	public hl7send(String hl7full, String apikey, String institutename) {
		super();
		this.hl7full = hl7full;
		this.apikey = apikey;
		this.institutename = institutename;
	}

	public String getHl7full() {
		return hl7full;
	}

	public void setHl7full(String hl7full) {
		this.hl7full = hl7full;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getInstitutename() {
		return institutename;
	}

	public void setInstitutename(String institutename) {
		this.institutename = institutename;
	}
}
