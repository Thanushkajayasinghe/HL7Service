package com.mpi.hl7.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hl7fields")
public class hl7 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long serial;

	private String sendingApplication;
	private String sendingFacility;
	private String receivingApplication;
	private String receivingFacility;
	private String dateTimeOfMessage;
	private String messageType;
	private String messageControlId;
	private String versionId;

	private String setId;
	private String patientId;
	private String patientIdentifierList;
	private String alternatePatientId;
	private String patientFamilyName;
	private String patientGivenName;
	private String secondAndFurtherGivenNames;
	private String patientNameSuffix;
	private String patientNamePrefix;
	private String motherMaidenName;
	private String dateTimeOfBirth;
	private String administrativeSex;
	private String patientAlias;
	private String race;
	private String patientAddressStreetAddress;
	private String patientAddressOtherDesignation;
	private String patientAddressCity;
	private String patientAddressStateOrProvince;
	private String patientAddressZipOrPostalCode;
	private String patientAddressCountry;
	private String countyCode;
	private String phoneNumberHome;
	private String phoneNumberBusiness;
	private String primaryLanguage;
	private String maritalStatus;
	private String religion;
	private String patientAccountNumber;
	private String ssnNumberPatient;
	private String driversLicenseNumberPatient;
	private String mothersIdentifier;
	private String ethnicGroup;
	private String birthPlace;
	private String multipleBirthIndicator;
	private String birthOrder;
	private String citizenship;
	private String veteransMilitaryStatus;
	private String nationality;
	private String patientDeathDateAndTime;
	private String patientDeathIndicator;
	private String identityUnknownIndicator;
	private String identityReliabilityCode;
	private String lastUpdateDateTime;
	private String lastUpdateFacility;
	private String taxonomicClassificationCode;
	private String breedCode;
	private String strain;
	private String productionClassCode;
	private String tribalCitizenship;
	private String patientTelecommunicationInformation;

	private String hl7full;

	// -----------------------------------------------

	public hl7() {
		
	}

	public hl7(String sendingApplication, String sendingFacility, String receivingApplication, String receivingFacility,
			String dateTimeOfMessage, String messageType, String messageControlId, String versionId, String setId,
			String patientId, String patientIdentifierList, String alternatePatientId, String patientFamilyName,
			String patientGivenName, String secondAndFurtherGivenNames, String patientNameSuffix,
			String patientNamePrefix, String motherMaidenName, String dateTimeOfBirth, String administrativeSex,
			String patientAlias, String race, String patientAddressStreetAddress, String patientAddressOtherDesignation,
			String patientAddressCity, String patientAddressStateOrProvince, String patientAddressZipOrPostalCode,
			String patientAddressCountry, String countyCode, String phoneNumberHome, String phoneNumberBusiness,
			String primaryLanguage, String maritalStatus, String religion, String patientAccountNumber,
			String ssnNumberPatient, String driversLicenseNumberPatient, String mothersIdentifier, String ethnicGroup,
			String birthPlace, String multipleBirthIndicator, String birthOrder, String citizenship,
			String veteransMilitaryStatus, String nationality, String patientDeathDateAndTime,
			String patientDeathIndicator, String identityUnknownIndicator, String identityReliabilityCode,
			String lastUpdateDateTime, String lastUpdateFacility, String taxonomicClassificationCode, String breedCode,
			String strain, String productionClassCode, String tribalCitizenship,
			String patientTelecommunicationInformation, String hl7full) {
		super();
		this.sendingApplication = sendingApplication;
		this.sendingFacility = sendingFacility;
		this.receivingApplication = receivingApplication;
		this.receivingFacility = receivingFacility;
		this.dateTimeOfMessage = dateTimeOfMessage;
		this.messageType = messageType;
		this.messageControlId = messageControlId;
		this.versionId = versionId;
		this.setId = setId;
		this.patientId = patientId;
		this.patientIdentifierList = patientIdentifierList;
		this.alternatePatientId = alternatePatientId;
		this.patientFamilyName = patientFamilyName;
		this.patientGivenName = patientGivenName;
		this.secondAndFurtherGivenNames = secondAndFurtherGivenNames;
		this.patientNameSuffix = patientNameSuffix;
		this.patientNamePrefix = patientNamePrefix;
		this.motherMaidenName = motherMaidenName;
		this.dateTimeOfBirth = dateTimeOfBirth;
		this.administrativeSex = administrativeSex;
		this.patientAlias = patientAlias;
		this.race = race;
		this.patientAddressStreetAddress = patientAddressStreetAddress;
		this.patientAddressOtherDesignation = patientAddressOtherDesignation;
		this.patientAddressCity = patientAddressCity;
		this.patientAddressStateOrProvince = patientAddressStateOrProvince;
		this.patientAddressZipOrPostalCode = patientAddressZipOrPostalCode;
		this.patientAddressCountry = patientAddressCountry;
		this.countyCode = countyCode;
		this.phoneNumberHome = phoneNumberHome;
		this.phoneNumberBusiness = phoneNumberBusiness;
		this.primaryLanguage = primaryLanguage;
		this.maritalStatus = maritalStatus;
		this.religion = religion;
		this.patientAccountNumber = patientAccountNumber;
		this.ssnNumberPatient = ssnNumberPatient;
		this.driversLicenseNumberPatient = driversLicenseNumberPatient;
		this.mothersIdentifier = mothersIdentifier;
		this.ethnicGroup = ethnicGroup;
		this.birthPlace = birthPlace;
		this.multipleBirthIndicator = multipleBirthIndicator;
		this.birthOrder = birthOrder;
		this.citizenship = citizenship;
		this.veteransMilitaryStatus = veteransMilitaryStatus;
		this.nationality = nationality;
		this.patientDeathDateAndTime = patientDeathDateAndTime;
		this.patientDeathIndicator = patientDeathIndicator;
		this.identityUnknownIndicator = identityUnknownIndicator;
		this.identityReliabilityCode = identityReliabilityCode;
		this.lastUpdateDateTime = lastUpdateDateTime;
		this.lastUpdateFacility = lastUpdateFacility;
		this.taxonomicClassificationCode = taxonomicClassificationCode;
		this.breedCode = breedCode;
		this.strain = strain;
		this.productionClassCode = productionClassCode;
		this.tribalCitizenship = tribalCitizenship;
		this.patientTelecommunicationInformation = patientTelecommunicationInformation;
		this.hl7full = hl7full;
	}

	// -----------------------------------------------

	public Long getSerial() {
		return serial;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
	}

	public String getSendingApplication() {
		return sendingApplication;
	}

	public void setSendingApplication(String sendingApplication) {
		this.sendingApplication = sendingApplication;
	}

	public String getSendingFacility() {
		return sendingFacility;
	}

	public void setSendingFacility(String sendingFacility) {
		this.sendingFacility = sendingFacility;
	}

	public String getReceivingApplication() {
		return receivingApplication;
	}

	public void setReceivingApplication(String receivingApplication) {
		this.receivingApplication = receivingApplication;
	}

	public String getReceivingFacility() {
		return receivingFacility;
	}

	public void setReceivingFacility(String receivingFacility) {
		this.receivingFacility = receivingFacility;
	}

	public String getDateTimeOfMessage() {
		return dateTimeOfMessage;
	}

	public void setDateTimeOfMessage(String dateTimeOfMessage) {
		this.dateTimeOfMessage = dateTimeOfMessage;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageControlId() {
		return messageControlId;
	}

	public void setMessageControlId(String messageControlId) {
		this.messageControlId = messageControlId;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getSetId() {
		return setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientIdentifierList() {
		return patientIdentifierList;
	}

	public void setPatientIdentifierList(String patientIdentifierList) {
		this.patientIdentifierList = patientIdentifierList;
	}

	public String getAlternatePatientId() {
		return alternatePatientId;
	}

	public void setAlternatePatientId(String alternatePatientId) {
		this.alternatePatientId = alternatePatientId;
	}

	public String getPatientFamilyName() {
		return patientFamilyName;
	}

	public void setPatientFamilyName(String patientFamilyName) {
		this.patientFamilyName = patientFamilyName;
	}

	public String getPatientGivenName() {
		return patientGivenName;
	}

	public void setPatientGivenName(String patientGivenName) {
		this.patientGivenName = patientGivenName;
	}

	public String getSecondAndFurtherGivenNames() {
		return secondAndFurtherGivenNames;
	}

	public void setSecondAndFurtherGivenNames(String secondAndFurtherGivenNames) {
		this.secondAndFurtherGivenNames = secondAndFurtherGivenNames;
	}

	public String getPatientNameSuffix() {
		return patientNameSuffix;
	}

	public void setPatientNameSuffix(String patientNameSuffix) {
		this.patientNameSuffix = patientNameSuffix;
	}

	public String getPatientNamePrefix() {
		return patientNamePrefix;
	}

	public void setPatientNamePrefix(String patientNamePrefix) {
		this.patientNamePrefix = patientNamePrefix;
	}

	public String getMotherMaidenName() {
		return motherMaidenName;
	}

	public void setMotherMaidenName(String motherMaidenName) {
		this.motherMaidenName = motherMaidenName;
	}

	public String getDateTimeOfBirth() {
		return dateTimeOfBirth;
	}

	public void setDateTimeOfBirth(String dateTimeOfBirth) {
		this.dateTimeOfBirth = dateTimeOfBirth;
	}

	public String getAdministrativeSex() {
		return administrativeSex;
	}

	public void setAdministrativeSex(String administrativeSex) {
		this.administrativeSex = administrativeSex;
	}

	public String getPatientAlias() {
		return patientAlias;
	}

	public void setPatientAlias(String patientAlias) {
		this.patientAlias = patientAlias;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getPatientAddressStreetAddress() {
		return patientAddressStreetAddress;
	}

	public void setPatientAddressStreetAddress(String patientAddressStreetAddress) {
		this.patientAddressStreetAddress = patientAddressStreetAddress;
	}

	public String getPatientAddressOtherDesignation() {
		return patientAddressOtherDesignation;
	}

	public void setPatientAddressOtherDesignation(String patientAddressOtherDesignation) {
		this.patientAddressOtherDesignation = patientAddressOtherDesignation;
	}

	public String getPatientAddressCity() {
		return patientAddressCity;
	}

	public void setPatientAddressCity(String patientAddressCity) {
		this.patientAddressCity = patientAddressCity;
	}

	public String getPatientAddressStateOrProvince() {
		return patientAddressStateOrProvince;
	}

	public void setPatientAddressStateOrProvince(String patientAddressStateOrProvince) {
		this.patientAddressStateOrProvince = patientAddressStateOrProvince;
	}

	public String getPatientAddressZipOrPostalCode() {
		return patientAddressZipOrPostalCode;
	}

	public void setPatientAddressZipOrPostalCode(String patientAddressZipOrPostalCode) {
		this.patientAddressZipOrPostalCode = patientAddressZipOrPostalCode;
	}

	public String getPatientAddressCountry() {
		return patientAddressCountry;
	}

	public void setPatientAddressCountry(String patientAddressCountry) {
		this.patientAddressCountry = patientAddressCountry;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getPhoneNumberHome() {
		return phoneNumberHome;
	}

	public void setPhoneNumberHome(String phoneNumberHome) {
		this.phoneNumberHome = phoneNumberHome;
	}

	public String getPhoneNumberBusiness() {
		return phoneNumberBusiness;
	}

	public void setPhoneNumberBusiness(String phoneNumberBusiness) {
		this.phoneNumberBusiness = phoneNumberBusiness;
	}

	public String getPrimaryLanguage() {
		return primaryLanguage;
	}

	public void setPrimaryLanguage(String primaryLanguage) {
		this.primaryLanguage = primaryLanguage;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getPatientAccountNumber() {
		return patientAccountNumber;
	}

	public void setPatientAccountNumber(String patientAccountNumber) {
		this.patientAccountNumber = patientAccountNumber;
	}

	public String getSsnNumberPatient() {
		return ssnNumberPatient;
	}

	public void setSsnNumberPatient(String ssnNumberPatient) {
		this.ssnNumberPatient = ssnNumberPatient;
	}

	public String getDriversLicenseNumberPatient() {
		return driversLicenseNumberPatient;
	}

	public void setDriversLicenseNumberPatient(String driversLicenseNumberPatient) {
		this.driversLicenseNumberPatient = driversLicenseNumberPatient;
	}

	public String getMothersIdentifier() {
		return mothersIdentifier;
	}

	public void setMothersIdentifier(String mothersIdentifier) {
		this.mothersIdentifier = mothersIdentifier;
	}

	public String getEthnicGroup() {
		return ethnicGroup;
	}

	public void setEthnicGroup(String ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getMultipleBirthIndicator() {
		return multipleBirthIndicator;
	}

	public void setMultipleBirthIndicator(String multipleBirthIndicator) {
		this.multipleBirthIndicator = multipleBirthIndicator;
	}

	public String getBirthOrder() {
		return birthOrder;
	}

	public void setBirthOrder(String birthOrder) {
		this.birthOrder = birthOrder;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getVeteransMilitaryStatus() {
		return veteransMilitaryStatus;
	}

	public void setVeteransMilitaryStatus(String veteransMilitaryStatus) {
		this.veteransMilitaryStatus = veteransMilitaryStatus;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPatientDeathDateAndTime() {
		return patientDeathDateAndTime;
	}

	public void setPatientDeathDateAndTime(String patientDeathDateAndTime) {
		this.patientDeathDateAndTime = patientDeathDateAndTime;
	}

	public String getPatientDeathIndicator() {
		return patientDeathIndicator;
	}

	public void setPatientDeathIndicator(String patientDeathIndicator) {
		this.patientDeathIndicator = patientDeathIndicator;
	}

	public String getIdentityUnknownIndicator() {
		return identityUnknownIndicator;
	}

	public void setIdentityUnknownIndicator(String identityUnknownIndicator) {
		this.identityUnknownIndicator = identityUnknownIndicator;
	}

	public String getIdentityReliabilityCode() {
		return identityReliabilityCode;
	}

	public void setIdentityReliabilityCode(String identityReliabilityCode) {
		this.identityReliabilityCode = identityReliabilityCode;
	}

	public String getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}

	public void setLastUpdateDateTime(String lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	public String getLastUpdateFacility() {
		return lastUpdateFacility;
	}

	public void setLastUpdateFacility(String lastUpdateFacility) {
		this.lastUpdateFacility = lastUpdateFacility;
	}

	public String getTaxonomicClassificationCode() {
		return taxonomicClassificationCode;
	}

	public void setTaxonomicClassificationCode(String taxonomicClassificationCode) {
		this.taxonomicClassificationCode = taxonomicClassificationCode;
	}

	public String getBreedCode() {
		return breedCode;
	}

	public void setBreedCode(String breedCode) {
		this.breedCode = breedCode;
	}

	public String getStrain() {
		return strain;
	}

	public void setStrain(String strain) {
		this.strain = strain;
	}

	public String getProductionClassCode() {
		return productionClassCode;
	}

	public void setProductionClassCode(String productionClassCode) {
		this.productionClassCode = productionClassCode;
	}

	public String getTribalCitizenship() {
		return tribalCitizenship;
	}

	public void setTribalCitizenship(String tribalCitizenship) {
		this.tribalCitizenship = tribalCitizenship;
	}

	public String getPatientTelecommunicationInformation() {
		return patientTelecommunicationInformation;
	}

	public void setPatientTelecommunicationInformation(String patientTelecommunicationInformation) {
		this.patientTelecommunicationInformation = patientTelecommunicationInformation;
	}

	public String getHl7full() {
		return hl7full;
	}

	public void setHl7full(String hl7full) {
		this.hl7full = hl7full;
	}

}
