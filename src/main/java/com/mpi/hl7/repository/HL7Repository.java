package com.mpi.hl7.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mpi.hl7.model.hl7;

public interface HL7Repository extends JpaRepository<hl7, Long> {

	// API select query
	@Query(value = "SELECT * FROM hl7fields WHERE message_control_id=?1", nativeQuery = true)
	Optional<hl7> findByMessageControlId(String phnno);

	// API search query
	@Query(value = "SELECT hl7full FROM hl7fields WHERE (message_control_id LIKE %?1%) OR (set_id LIKE %?2%) OR (patient_id LIKE %?3%) OR "
			+ "(patient_family_name LIKE %?4%) OR (patient_given_name LIKE %?5%) OR (second_and_further_given_names LIKE %?6%) OR "
			+ "(mother_maiden_name LIKE %?7%) OR (date_time_of_birth LIKE %?8%) OR (administrative_sex LIKE %?9%) OR (patient_address_street_address LIKE %?10%) OR "
			+ "(patient_address_other_designation LIKE %?11%) OR (patient_address_city LIKE %?12%) OR (patient_address_state_or_province LIKE %?13%) OR "
			+ "(patient_address_zip_or_postal_code LIKE %?14%) OR (patient_address_country LIKE %?15%) OR (phone_number_home LIKE %?16%) OR "
			+ "(phone_number_business LIKE %?17%) OR (marital_status LIKE %?18%) OR (religion LIKE %?19%) OR (patient_account_number LIKE %?20%) OR "
			+ "(ssn_number_patient LIKE %?21%) OR (drivers_license_number_patient LIKE %?22%) OR (citizenship LIKE %?23%) OR "
			+ "(nationality LIKE %?24%) OR (patient_death_date_and_time LIKE %?25%)", nativeQuery = true)
	List<hl7FullOnly> searchHl7Messages(String messageControlId, String setId, String patientId,
			String patientFamilyName, String patientGivenName, String secondAndFurtherGivenNames,
			String motherMaidenName, String dateTimeOfBirth, String administrativeSex,
			String patientAddressStreetAddress, String patientAddressOtherDesignation, String patientAddressCity,
			String patientAddressStateOrProvince, String patientAddressZipOrPostalCode, String patientAddressCountry,
			String phoneNumberHome, String phoneNumberBusiness, String maritalStatus, String religion,
			String patientAccountNumber, String ssnNumberPatient, String driversLicenseNumberPatient,
			String citizenship, String nationality, String patientDeathDateAndTime);

	//Interface for get only hl7 message
	interface hl7FullOnly {
		String getHl7full();
	}


	@Query(value = "SELECT * FROM hl7fields WHERE message_control_id=?1", nativeQuery = true)
	hl7 findByMessageControlId2(String messageControlId);

	@Query(value = "SELECT * FROM hl7fields WHERE message_control_id=?1", nativeQuery = true)
	List<hl7FullOnly> histroryHl7(String messageControlId);

	@Query(value = "SELECT COUNT(message_control_id) as count from hl7fields WHERE message_control_id=?1", nativeQuery = true)
	Integer checkPhnAlreadyExists(String messageControlId);

	@Query(value = "SELECT COUNT(apikey) as count FROM api_keys WHERE apikey=?1 and institutename=?2", nativeQuery = true)
	Integer checkApiKey(String apiKey, String instituteName);

}