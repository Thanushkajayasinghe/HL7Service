package com.mpi.hl7.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpi.hl7.model.hl7;
import com.mpi.hl7.model.hl7history;
import com.mpi.hl7.model.hl7send;
import com.mpi.hl7.model.loghl7;
import com.mpi.hl7.repository.HL7Repository;
import com.mpi.hl7.repository.HL7Repository.hl7FullOnly;
import com.mpi.hl7.repository.Hl7HistoryRepo;
import com.mpi.hl7.repository.LogHl7Repo;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;
import ca.uhn.hl7v2.validation.impl.ValidationContextFactory;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MPIController {

	@Autowired
	private HL7Repository hl7Repo;
	@Autowired
	private Hl7HistoryRepo hl7HistoryRepo;
	@Autowired
	private LogHl7Repo logHl7Repo;

	@GetMapping("/")
	public String welcome(){
		return "Welcome to MPI Cloud Solution!";	
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveHl7(@RequestBody hl7send hl7data) throws HL7Exception, JSONException {

		String msg = hl7data.getHl7full();
		String apiKey = hl7data.getApikey();
		String instituteName = hl7data.getInstitutename();

		JSONObject jsonObject = new JSONObject();

		if (apiKey == null || apiKey == "") {
			jsonObject.put("Response", "Api key can't be empty");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

		if (instituteName == null || instituteName == "") {
			jsonObject.put("Response", "Institute name can't be empty");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

		Integer countA = hl7Repo.checkApiKey(apiKey, instituteName);
		if (countA == 1) {

			HapiContext context = new DefaultHapiContext();
			context.setValidationContext(ValidationContextFactory.defaultValidation());
			PipeParser parser = context.getPipeParser();

			try {
				parser.parse(msg);

				context.getParserConfiguration().setValidating(false);
				Parser p = context.getGenericParser();
				Message hapiMsg = p.parse(msg);

				Terser terser = new Terser(hapiMsg);

				// ------------ MSH Fields ------------------------------

				String sendingApplication = terser.get("/.MSH-3-1");
				String sendingFacility = terser.get("/.MSH-4-1");
				String receivingApplication = terser.get("/.MSH-5-1");
				String receivingFacility = terser.get("/.MSH-6-1");
				String dateTimeOfMessage = terser.get("/.MSH-7-1");
				String messageType = terser.get("/.MSH-9-1");
				String messageControlId = terser.get("/.MSH-10-1");
				String versionId = terser.get("/.MSH-12-1");

				// ------------ PID Fields ------------------------------

				String setId = terser.get("/.PID-1-1");
				String patientId = terser.get("/.PID-2-1");
				String patientIdentifierList = terser.get("/.PID-3-1");
				String alternatePatientId = terser.get("/.PID-4-1");
				String patientFamilyName = terser.get("/.PID-5-1");
				String patientGivenName = terser.get("/.PID-5-2");
				String secondAndFurtherGivenNames = terser.get("/.PID-5-3");
				String patientNameSuffix = terser.get("/.PID-5-4");
				String patientNamePrefix = terser.get("/.PID-5-5");
				String motherMaidenName = terser.get("/.PID-6-1");
				String dateTimeOfBirth = terser.get("/.PID-7-1");
				String administrativeSex = terser.get("/.PID-8-1");
				String patientAlias = terser.get("/.PID-9-1");
				String race = terser.get("/.PID-10-1");
				String patientAddressStreetAddress = terser.get("/.PID-11-1");
				String patientAddressOtherDesignation = terser.get("/.PID-11-2");
				String patientAddressCity = terser.get("/.PID-11-3");
				String patientAddressStateOrProvince = terser.get("/.PID-11-4");
				String patientAddressZipOrPostalCode = terser.get("/.PID-11-5");
				String patientAddressCountry = terser.get("/.PID-11-6");
				String countyCode = terser.get("/.PID-12-1");
				String phoneNumberHome = terser.get("/.PID-13-1");
				String phoneNumberBusiness = terser.get("/.PID-14-1");
				String primaryLanguage = terser.get("/.PID-15-1");
				String maritalStatus = terser.get("/.PID-16-1");
				String religion = terser.get("/.PID-17-1");
				String patientAccountNumber = terser.get("/.PID-18-1");
				String ssnNumberPatient = terser.get("/.PID-19-1");
				String driversLicenseNumberPatient = terser.get("/.PID-20-1");
				String mothersIdentifier = terser.get("/.PID-21-1");
				String ethnicGroup = terser.get("/.PID-22-1");
				String birthPlace = terser.get("/.PID-23-1");
				String multipleBirthIndicator = terser.get("/.PID-24-1");
				String birthOrder = terser.get("/.PID-25-1");
				String citizenship = terser.get("/.PID-26-1");
				String veteransMilitaryStatus = terser.get("/.PID-27-1");
				String nationality = terser.get("/.PID-28-1");
				String patientDeathDateAndTime = terser.get("/.PID-29-1");
				String patientDeathIndicator = terser.get("/.PID-30-1");
				String identityUnknownIndicator = terser.get("/.PID-31-1");
				String identityReliabilityCode = terser.get("/.PID-32-1");
				String lastUpdateDateTime = terser.get("/.PID-33-1");
				String lastUpdateFacility = terser.get("/.PID-34-1");
				String taxonomicClassificationCode = terser.get("/.PID-35-1");
				String breedCode = terser.get("/.PID-36-1");
				String strain = terser.get("/.PID-37-1");
				String productionClassCode = terser.get("/.PID-38-1");
				String tribalCitizenship = terser.get("/.PID-39-1");
				String patientTelecommunicationInformation = terser.get("/.PID-40-1");

				String hl7full = msg;

				hl7 hl7params = new hl7();

				hl7params.setSendingApplication(sendingApplication);
				hl7params.setSendingFacility(sendingFacility);
				hl7params.setReceivingApplication(receivingApplication);
				hl7params.setReceivingFacility(receivingFacility);
				hl7params.setDateTimeOfMessage(dateTimeOfMessage);
				hl7params.setMessageType(messageType);
				hl7params.setMessageControlId(messageControlId);
				hl7params.setVersionId(versionId);

				hl7params.setSetId(setId);
				hl7params.setPatientId(patientId);
				hl7params.setPatientIdentifierList(patientIdentifierList);
				hl7params.setAlternatePatientId(alternatePatientId);
				hl7params.setPatientFamilyName(patientFamilyName);
				hl7params.setPatientGivenName(patientGivenName);
				hl7params.setSecondAndFurtherGivenNames(secondAndFurtherGivenNames);
				hl7params.setPatientNameSuffix(patientNameSuffix);
				hl7params.setPatientNamePrefix(patientNamePrefix);
				hl7params.setMotherMaidenName(motherMaidenName);
				hl7params.setDateTimeOfBirth(dateTimeOfBirth);
				hl7params.setAdministrativeSex(administrativeSex);
				hl7params.setPatientAlias(patientAlias);
				hl7params.setRace(race);
				hl7params.setPatientAddressStreetAddress(patientAddressStreetAddress);
				hl7params.setPatientAddressOtherDesignation(patientAddressOtherDesignation);
				hl7params.setPatientAddressCity(patientAddressCity);
				hl7params.setPatientAddressStateOrProvince(patientAddressStateOrProvince);
				hl7params.setPatientAddressZipOrPostalCode(patientAddressZipOrPostalCode);
				hl7params.setPatientAddressCountry(patientAddressCountry);
				hl7params.setCountyCode(countyCode);
				hl7params.setPhoneNumberHome(phoneNumberHome);
				hl7params.setPhoneNumberBusiness(phoneNumberBusiness);
				hl7params.setPrimaryLanguage(primaryLanguage);
				hl7params.setMaritalStatus(maritalStatus);
				hl7params.setReligion(religion);
				hl7params.setPatientAccountNumber(patientAccountNumber);
				hl7params.setSsnNumberPatient(ssnNumberPatient);
				hl7params.setDriversLicenseNumberPatient(driversLicenseNumberPatient);
				hl7params.setMothersIdentifier(mothersIdentifier);
				hl7params.setEthnicGroup(ethnicGroup);
				hl7params.setBirthPlace(birthPlace);
				hl7params.setMultipleBirthIndicator(multipleBirthIndicator);
				hl7params.setBirthOrder(birthOrder);
				hl7params.setCitizenship(citizenship);
				hl7params.setVeteransMilitaryStatus(veteransMilitaryStatus);
				hl7params.setNationality(nationality);
				hl7params.setPatientDeathDateAndTime(patientDeathDateAndTime);
				hl7params.setPatientDeathIndicator(patientDeathIndicator);
				hl7params.setIdentityUnknownIndicator(identityUnknownIndicator);
				hl7params.setIdentityReliabilityCode(identityReliabilityCode);
				hl7params.setLastUpdateDateTime(lastUpdateDateTime);
				hl7params.setLastUpdateFacility(lastUpdateFacility);
				hl7params.setTaxonomicClassificationCode(taxonomicClassificationCode);
				hl7params.setBreedCode(breedCode);
				hl7params.setStrain(strain);
				hl7params.setProductionClassCode(productionClassCode);
				hl7params.setTribalCitizenship(tribalCitizenship);
				hl7params.setPatientTelecommunicationInformation(patientTelecommunicationInformation);

				hl7params.setHl7full(hl7full);

				// log add
				loghl7 log = new loghl7();
				log.setHl7full(msg);
				log.setApi("create");

				String dataMessage = "";
				Integer count = hl7Repo.checkPhnAlreadyExists(messageControlId);
				if (count == 0) {
					hl7Repo.save(hl7params);

					Long serial = hl7params.getSerial();

					hl7history hl7history = new hl7history();

					hl7history.setSerial(serial);
					hl7history.setHl7full(msg);

					LocalDateTime now = LocalDateTime.now();
					DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

					hl7history.setCdate(datetime.format(now));

					hl7HistoryRepo.save(hl7history);

					log.setStatus("success");
					log.setCdate(datetime.format(now));
					log.setCby(apiKey);

					logHl7Repo.save(log);

					dataMessage = "success";
				} else {

					LocalDateTime now = LocalDateTime.now();
					DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

					log.setStatus("PHN already exists");
					log.setCdate(datetime.format(now));
					log.setCby(apiKey);

					logHl7Repo.save(log);

					dataMessage = "phnexsists";
				}

				jsonObject.put("Response", dataMessage);
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);

			} catch (HL7Exception e) {

				// log add
				loghl7 log = new loghl7();
				log.setHl7full(msg);
				log.setApi("create");
				log.setStatus(e.toString());
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				log.setCdate(datetime.format(now));
				log.setCby(apiKey);

				logHl7Repo.save(log);

				jsonObject.put("Response", e);
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			}
		} else {

			// log add
			loghl7 log = new loghl7();
			log.setHl7full(msg);
			log.setApi("create");
			log.setStatus("Invalid Api or Institute Name");
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			log.setCdate(datetime.format(now));
			log.setCby(apiKey);

			logHl7Repo.save(log);

			jsonObject.put("Response", "Invalid Api or Institute Name");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}
	}

	@PostMapping("/select")
	public ResponseEntity<?> selectHl7(@RequestBody hl7send hl7send) throws HL7Exception, IOException, JSONException {

		String msg = hl7send.getHl7full();
		String apiKey = hl7send.getApikey();
		String instituteName = hl7send.getInstitutename();

		JSONObject jsonObject = new JSONObject();

		if (apiKey == null || apiKey == "") {
			jsonObject.put("Response", "Api key can't be empty");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

		if (instituteName == null || instituteName == "") {
			jsonObject.put("Response", "Institute name can't be empty");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

		Integer countA = hl7Repo.checkApiKey(apiKey, instituteName);
		if (countA == 1) {

			HapiContext context = new DefaultHapiContext();
			context.setValidationContext(ValidationContextFactory.defaultValidation());
			PipeParser parser = context.getPipeParser();

			try {

				parser.parse(msg);

				String[] MSHSegment = msg.split("(?<=PID)");
				String[] MSHSegmentSeparated = MSHSegment[0].split(Pattern.quote("|"));
				String RecivedApplication = MSHSegmentSeparated[2];
				String RecivedFacility = MSHSegmentSeparated[3];
				MSHSegmentSeparated[2] = "MPI";
				MSHSegmentSeparated[3] = "SLIT";

				MSHSegmentSeparated[4] = RecivedApplication;
				MSHSegmentSeparated[5] = RecivedFacility;

				DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
				Date date = new Date();
				MSHSegmentSeparated[6] = dateFormat.format(date);

				String joinedMSH = String.join("|", MSHSegmentSeparated);

				// ------------- get saved hl7 from db --------------------------
				context.getParserConfiguration().setValidating(false);
				Parser p = context.getGenericParser();

				Message hapiMsg = p.parse(msg);

				Terser terser = new Terser(hapiMsg);
				String phnno = terser.get("/.MSH-10-1");

				Optional<hl7> data = hl7Repo.findByMessageControlId(phnno);
				String msgHl7 = data.get().getHl7full().toString();

				String[] MSHSegments = msgHl7.split("(?<=PID)");

				// log add
				loghl7 log = new loghl7();
				log.setHl7full(msg);
				log.setApi("select");
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				log.setStatus("success");
				log.setCdate(datetime.format(now));
				log.setCby(apiKey);

				logHl7Repo.save(log);

				jsonObject.put("Response", joinedMSH + "\r\n" + MSHSegments[1]);
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			} catch (HL7Exception e) {

				// log add
				loghl7 log = new loghl7();
				log.setHl7full(msg);
				log.setApi("select");
				log.setStatus(e.toString());
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				log.setCdate(datetime.format(now));
				log.setCby(apiKey);

				logHl7Repo.save(log);

				jsonObject.put("Response", e);
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			}
		} else {

			// log add
			loghl7 log = new loghl7();
			log.setHl7full(msg);
			log.setApi("select");
			log.setStatus("Invalid Api or Institute Name");
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			log.setCdate(datetime.format(now));
			log.setCby(apiKey);

			logHl7Repo.save(log);

			jsonObject.put("Response", "Invalid Api or Institute Name");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}
	}

	@PostMapping("/search")
	public ResponseEntity<?> searchHl7(@RequestBody hl7send hl7send) throws HL7Exception, IOException, JSONException {

		String msg = hl7send.getHl7full();
		String apiKey = hl7send.getApikey();
		String instituteName = hl7send.getInstitutename();

		JSONObject jsonObject = new JSONObject();

		if (apiKey == null || apiKey == "") {
			jsonObject.put("Response", "Api key can't be empty");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

		if (instituteName == null || instituteName == "") {
			jsonObject.put("Response", "Institute name can't be empty");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

		Integer countA = hl7Repo.checkApiKey(apiKey, instituteName);
		if (countA == 1) {

			HapiContext context = new DefaultHapiContext();
			context.setValidationContext(ValidationContextFactory.defaultValidation());
			PipeParser parser = context.getPipeParser();

			try {

				context.getParserConfiguration().setValidating(false);
				Parser p = context.getGenericParser();
				Message hapiMsg = p.parse(msg);

				Terser terser = new Terser(hapiMsg);

				// ------------ MSH Fields ------------------------------

				String sendingApplication = terser.get("/.MSH-3-1");
				String sendingFacility = terser.get("/.MSH-4-1");
				String receivingApplication = terser.get("/.MSH-5-1");
				String receivingFacility = terser.get("/.MSH-6-1");
				String dateTimeOfMessage = terser.get("/.MSH-7-1");
				String messageType = terser.get("/.MSH-9-1");
				String messageControlId = terser.get("/.MSH-10-1");
				String versionId = terser.get("/.MSH-12-1");

				// ------------ PID Fields ------------------------------

				String setId = terser.get("/.PID-1-1");
				String patientId = ((terser.get("/.PID-2-1") == null) ? "" : terser.get("/.PID-2-1"));
				String patientIdentifierList = ((terser.get("/.PID-3-1") == null) ? "" : terser.get("/.PID-3-1"));
				String alternatePatientId = ((terser.get("/.PID-4-1") == null) ? "" : terser.get("/.PID-4-1"));
				String patientFamilyName = ((terser.get("/.PID-5-1") == null) ? "" : terser.get("/.PID-5-1"));
				String patientGivenName = ((terser.get("/.PID-5-2") == null) ? "" : terser.get("/.PID-5-2"));
				String secondAndFurtherGivenNames = ((terser.get("/.PID-5-3") == null) ? "" : terser.get("/.PID-5-3"));
				String patientNameSuffix = ((terser.get("/.PID-5-4") == null) ? "" : terser.get("/.PID-5-4"));
				String patientNamePrefix = ((terser.get("/.PID-5-5") == null) ? "" : terser.get("/.PID-5-5"));
				String motherMaidenName = ((terser.get("/.PID-6-1") == null) ? "" : terser.get("/.PID-6-1"));
				String dateTimeOfBirth = ((terser.get("/.PID-7-1") == null) ? "" : terser.get("/.PID-7-1"));
				String administrativeSex = ((terser.get("/.PID-8-1") == null) ? "" : terser.get("/.PID-8-1"));
				String patientAlias = ((terser.get("/.PID-9-1") == null) ? "" : terser.get("/.PID-9-1"));
				String race = ((terser.get("/.PID-10-1") == null) ? "" : terser.get("/.PID-10-1"));
				String patientAddressStreetAddress = ((terser.get("/.PID-11-1") == null) ? ""
						: terser.get("/.PID-11-1"));
				String patientAddressOtherDesignation = ((terser.get("/.PID-11-2") == null) ? ""
						: terser.get("/.PID-11-2"));
				String patientAddressCity = ((terser.get("/.PID-11-3") == null) ? "" : terser.get("/.PID-11-3"));
				String patientAddressStateOrProvince = ((terser.get("/.PID-11-4") == null) ? ""
						: terser.get("/.PID-11-4"));
				String patientAddressZipOrPostalCode = ((terser.get("/.PID-11-5") == null) ? ""
						: terser.get("/.PID-11-5"));
				String patientAddressCountry = ((terser.get("/.PID-11-6") == null) ? "" : terser.get("/.PID-11-6"));
				String countyCode = ((terser.get("/.PID-12-1") == null) ? "" : terser.get("/.PID-12-1"));
				String phoneNumberHome = ((terser.get("/.PID-13-1") == null) ? "" : terser.get("/.PID-13-1"));
				String phoneNumberBusiness = ((terser.get("/.PID-14-1") == null) ? "" : terser.get("/.PID-14-1"));
				String primaryLanguage = ((terser.get("/.PID-15-1") == null) ? "" : terser.get("/.PID-15-1"));
				String maritalStatus = ((terser.get("/.PID-16-1") == null) ? "" : terser.get("/.PID-16-1"));
				String religion = ((terser.get("/.PID-17-1") == null) ? "" : terser.get("/.PID-17-1"));
				String patientAccountNumber = ((terser.get("/.PID-18-1") == null) ? "" : terser.get("/.PID-18-1"));
				String ssnNumberPatient = ((terser.get("/.PID-19-1") == null) ? "" : terser.get("/.PID-19-1"));
				String driversLicenseNumberPatient = ((terser.get("/.PID-20-1") == null) ? ""
						: terser.get("/.PID-20-1"));
				String mothersIdentifier = ((terser.get("/.PID-21-1") == null) ? "" : terser.get("/.PID-21-1"));
				String ethnicGroup = ((terser.get("/.PID-22-1") == null) ? "" : terser.get("/.PID-22-1"));
				String birthPlace = ((terser.get("/.PID-23-1") == null) ? "" : terser.get("/.PID-23-1"));
				String multipleBirthIndicator = ((terser.get("/.PID-24-1") == null) ? "" : terser.get("/.PID-24-1"));
				String birthOrder = ((terser.get("/.PID-25-1") == null) ? "" : terser.get("/.PID-25-1"));
				String citizenship = ((terser.get("/.PID-26-1") == null) ? "" : terser.get("/.PID-26-1"));
				String veteransMilitaryStatus = ((terser.get("/.PID-27-1") == null) ? "" : terser.get("/.PID-27-1"));
				String nationality = ((terser.get("/.PID-28-1") == null) ? "" : terser.get("/.PID-28-1"));
				String patientDeathDateAndTime = ((terser.get("/.PID-29-1") == null) ? "" : terser.get("/.PID-29-1"));

				List<hl7FullOnly> data = hl7Repo.searchHl7Messages(messageControlId, setId, patientId,
						patientFamilyName, patientGivenName, secondAndFurtherGivenNames, motherMaidenName,
						dateTimeOfBirth, administrativeSex, patientAddressStreetAddress, patientAddressOtherDesignation,
						patientAddressCity, patientAddressStateOrProvince, patientAddressZipOrPostalCode,
						patientAddressCountry, phoneNumberHome, phoneNumberBusiness, maritalStatus, religion,
						patientAccountNumber, ssnNumberPatient, driversLicenseNumberPatient, citizenship, nationality,
						patientDeathDateAndTime);

				List<String> list = new ArrayList<>();
				for (hl7FullOnly hl7 : data) {
					String msg1 = (hl7.getHl7full());

					String[] MSHSegment = msg1.split("(?<=PID)");
					String[] MSHSegmentSeparated = MSHSegment[0].split(Pattern.quote("|"));
					String RecivedApplication = MSHSegmentSeparated[2];
					String RecivedFacility = MSHSegmentSeparated[3];
					MSHSegmentSeparated[2] = "MPI";
					MSHSegmentSeparated[3] = "SLIT";

					MSHSegmentSeparated[4] = RecivedApplication;
					MSHSegmentSeparated[5] = RecivedFacility;

					DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
					Date date = new Date();
					MSHSegmentSeparated[6] = dateFormat.format(date);

					String joinedMSH = String.join("|", MSHSegmentSeparated);
					String fullMsg = joinedMSH + "\r\n" + MSHSegment[1];

					Collections.addAll(list, fullMsg);
				}

				// log add
				loghl7 log = new loghl7();
				log.setHl7full(msg);
				log.setApi("search");
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				log.setStatus("success");
				log.setCdate(datetime.format(now));
				log.setCby(apiKey);

				logHl7Repo.save(log);

				jsonObject.put("Response", list);
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);

			} catch (HL7Exception e) {

				// log add
				loghl7 log = new loghl7();
				log.setHl7full(msg);
				log.setApi("search");
				log.setStatus(e.toString());
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				log.setCdate(datetime.format(now));
				log.setCby(apiKey);

				logHl7Repo.save(log);

				jsonObject.put("Response", "Invalid HL7 Message");
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			}
		} else {

			// log add
			loghl7 log = new loghl7();
			log.setHl7full(msg);
			log.setApi("search");
			log.setStatus("Invalid Api or Institute Name");
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			log.setCdate(datetime.format(now));
			log.setCby(apiKey);

			logHl7Repo.save(log);

			jsonObject.put("Response", "Invalid Api or Institute Name");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

	}

	@PostMapping("/update")
	public ResponseEntity<?> updateHl7(@RequestBody hl7send hl7data) throws HL7Exception, JSONException {

		String msg = hl7data.getHl7full();
		String apiKey = hl7data.getApikey();
		String instituteName = hl7data.getInstitutename();

		JSONObject jsonObject = new JSONObject();

		if (apiKey == null || apiKey == "") {
			jsonObject.put("Response", "Api key can't be empty");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

		if (instituteName == null || instituteName == "") {
			jsonObject.put("Response", "Institute name can't be empty");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

		Integer countA = hl7Repo.checkApiKey(apiKey, instituteName);
		if (countA == 1) {

			HapiContext context = new DefaultHapiContext();
			context.setValidationContext(ValidationContextFactory.defaultValidation());
			PipeParser parser = context.getPipeParser();

			try {

				context.getParserConfiguration().setValidating(false);
				Parser p = context.getGenericParser();
				Message hapiMsg = p.parse(msg);

				Terser terser = new Terser(hapiMsg);

				// ------------ MSH Fields ------------------------------

				String sendingApplication = terser.get("/.MSH-3-1");
				String sendingFacility = terser.get("/.MSH-4-1");
				String receivingApplication = terser.get("/.MSH-5-1");
				String receivingFacility = terser.get("/.MSH-6-1");
				String dateTimeOfMessage = terser.get("/.MSH-7-1");
				String messageType = terser.get("/.MSH-9-1");
				String messageControlId = terser.get("/.MSH-10-1");
				String versionId = terser.get("/.MSH-12-1");

				// ------------ PID Fields ------------------------------

				String setId = terser.get("/.PID-1-1");
				String patientId = terser.get("/.PID-2-1");
				String patientIdentifierList = terser.get("/.PID-3-1");
				String alternatePatientId = terser.get("/.PID-4-1");
				String patientFamilyName = terser.get("/.PID-5-1");
				String patientGivenName = terser.get("/.PID-5-2");
				String secondAndFurtherGivenNames = terser.get("/.PID-5-3");
				String patientNameSuffix = terser.get("/.PID-5-4");
				String patientNamePrefix = terser.get("/.PID-5-5");
				String motherMaidenName = terser.get("/.PID-6-1");
				String dateTimeOfBirth = terser.get("/.PID-7-1");
				String administrativeSex = terser.get("/.PID-8-1");
				String patientAlias = terser.get("/.PID-9-1");
				String race = terser.get("/.PID-10-1");
				String patientAddressStreetAddress = terser.get("/.PID-11-1");
				String patientAddressOtherDesignation = terser.get("/.PID-11-2");
				String patientAddressCity = terser.get("/.PID-11-3");
				String patientAddressStateOrProvince = terser.get("/.PID-11-4");
				String patientAddressZipOrPostalCode = terser.get("/.PID-11-5");
				String patientAddressCountry = terser.get("/.PID-11-6");
				String countyCode = terser.get("/.PID-12-1");
				String phoneNumberHome = terser.get("/.PID-13-1");
				String phoneNumberBusiness = terser.get("/.PID-14-1");
				String primaryLanguage = terser.get("/.PID-15-1");
				String maritalStatus = terser.get("/.PID-16-1");
				String religion = terser.get("/.PID-17-1");
				String patientAccountNumber = terser.get("/.PID-18-1");
				String ssnNumberPatient = terser.get("/.PID-19-1");
				String driversLicenseNumberPatient = terser.get("/.PID-20-1");
				String mothersIdentifier = terser.get("/.PID-21-1");
				String ethnicGroup = terser.get("/.PID-22-1");
				String birthPlace = terser.get("/.PID-23-1");
				String multipleBirthIndicator = terser.get("/.PID-24-1");
				String birthOrder = terser.get("/.PID-25-1");
				String citizenship = terser.get("/.PID-26-1");
				String veteransMilitaryStatus = terser.get("/.PID-27-1");
				String nationality = terser.get("/.PID-28-1");
				String patientDeathDateAndTime = terser.get("/.PID-29-1");
				String patientDeathIndicator = terser.get("/.PID-30-1");
				String identityUnknownIndicator = terser.get("/.PID-31-1");
				String identityReliabilityCode = terser.get("/.PID-32-1");
				String lastUpdateDateTime = terser.get("/.PID-33-1");
				String lastUpdateFacility = terser.get("/.PID-34-1");
				String taxonomicClassificationCode = terser.get("/.PID-35-1");
				String breedCode = terser.get("/.PID-36-1");
				String strain = terser.get("/.PID-37-1");
				String productionClassCode = terser.get("/.PID-38-1");
				String tribalCitizenship = terser.get("/.PID-39-1");
				String patientTelecommunicationInformation = terser.get("/.PID-40-1");

				String hl7full = msg;

				hl7 hl7params = new hl7();

				hl7params = hl7Repo.findByMessageControlId2(messageControlId);

				hl7params.setSendingApplication(sendingApplication);
				hl7params.setSendingFacility(sendingFacility);
				hl7params.setReceivingApplication(receivingApplication);
				hl7params.setReceivingFacility(receivingFacility);
				hl7params.setDateTimeOfMessage(dateTimeOfMessage);
				hl7params.setMessageType(messageType);
				hl7params.setVersionId(versionId);

				hl7params.setSetId(setId);
				hl7params.setPatientId(patientId);
				hl7params.setPatientIdentifierList(patientIdentifierList);
				hl7params.setAlternatePatientId(alternatePatientId);
				hl7params.setPatientFamilyName(patientFamilyName);
				hl7params.setPatientGivenName(patientGivenName);
				hl7params.setSecondAndFurtherGivenNames(secondAndFurtherGivenNames);
				hl7params.setPatientNameSuffix(patientNameSuffix);
				hl7params.setPatientNamePrefix(patientNamePrefix);
				hl7params.setMotherMaidenName(motherMaidenName);
				hl7params.setDateTimeOfBirth(dateTimeOfBirth);
				hl7params.setAdministrativeSex(administrativeSex);
				hl7params.setPatientAlias(patientAlias);
				hl7params.setRace(race);
				hl7params.setPatientAddressStreetAddress(patientAddressStreetAddress);
				hl7params.setPatientAddressOtherDesignation(patientAddressOtherDesignation);
				hl7params.setPatientAddressCity(patientAddressCity);
				hl7params.setPatientAddressStateOrProvince(patientAddressStateOrProvince);
				hl7params.setPatientAddressZipOrPostalCode(patientAddressZipOrPostalCode);
				hl7params.setPatientAddressCountry(patientAddressCountry);
				hl7params.setCountyCode(countyCode);
				hl7params.setPhoneNumberHome(phoneNumberHome);
				hl7params.setPhoneNumberBusiness(phoneNumberBusiness);
				hl7params.setPrimaryLanguage(primaryLanguage);
				hl7params.setMaritalStatus(maritalStatus);
				hl7params.setReligion(religion);
				hl7params.setPatientAccountNumber(patientAccountNumber);
				hl7params.setSsnNumberPatient(ssnNumberPatient);
				hl7params.setDriversLicenseNumberPatient(driversLicenseNumberPatient);
				hl7params.setMothersIdentifier(mothersIdentifier);
				hl7params.setEthnicGroup(ethnicGroup);
				hl7params.setBirthPlace(birthPlace);
				hl7params.setMultipleBirthIndicator(multipleBirthIndicator);
				hl7params.setBirthOrder(birthOrder);
				hl7params.setCitizenship(citizenship);
				hl7params.setVeteransMilitaryStatus(veteransMilitaryStatus);
				hl7params.setNationality(nationality);
				hl7params.setPatientDeathDateAndTime(patientDeathDateAndTime);
				hl7params.setPatientDeathIndicator(patientDeathIndicator);
				hl7params.setIdentityUnknownIndicator(identityUnknownIndicator);
				hl7params.setIdentityReliabilityCode(identityReliabilityCode);
				hl7params.setLastUpdateDateTime(lastUpdateDateTime);
				hl7params.setLastUpdateFacility(lastUpdateFacility);
				hl7params.setTaxonomicClassificationCode(taxonomicClassificationCode);
				hl7params.setBreedCode(breedCode);
				hl7params.setStrain(strain);
				hl7params.setProductionClassCode(productionClassCode);
				hl7params.setTribalCitizenship(tribalCitizenship);
				hl7params.setPatientTelecommunicationInformation(patientTelecommunicationInformation);

				hl7params.setHl7full(hl7full);

				hl7Repo.save(hl7params);

				Long serial = hl7params.getSerial();

				hl7history hl7history = new hl7history();

				hl7history.setSerial(serial);
				hl7history.setHl7full(msg);

				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

				hl7history.setCdate(datetime.format(now));

				hl7HistoryRepo.save(hl7history);

				// log add
				loghl7 log = new loghl7();
				log.setHl7full(msg);
				log.setApi("update");
				log.setStatus("success");
				log.setCdate(datetime.format(now));
				log.setCby(apiKey);

				logHl7Repo.save(log);

				jsonObject.put("Response", "success");
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);

			} catch (HL7Exception e) {

				// log add
				loghl7 log = new loghl7();
				log.setHl7full(msg);
				log.setApi("update");
				log.setStatus(e.toString());
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				log.setCdate(datetime.format(now));
				log.setCby(apiKey);

				logHl7Repo.save(log);

				jsonObject.put("Response", e);
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			}
		} else {

			// log add
			loghl7 log = new loghl7();
			log.setHl7full(msg);
			log.setApi("update");
			log.setStatus("Invalid Api or Institute Name");
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			log.setCdate(datetime.format(now));
			log.setCby(apiKey);

			logHl7Repo.save(log);

			jsonObject.put("Response", "Invalid Api or Institute Name");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

	}

	@PostMapping("/history")
	public ResponseEntity<?> loadHistoryHl7(@RequestBody hl7send hl7send)
			throws HL7Exception, IOException, JSONException {

		String msg = hl7send.getHl7full();
		String apiKey = hl7send.getApikey();
		String instituteName = hl7send.getInstitutename();

		JSONObject jsonObject = new JSONObject();

		if (apiKey == null || apiKey == "") {
			jsonObject.put("Response", "Api key can't be empty");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

		if (instituteName == null || instituteName == "") {
			jsonObject.put("Response", "Institute name can't be empty");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

		Integer countA = hl7Repo.checkApiKey(apiKey, instituteName);
		if (countA == 1) {

			HapiContext context = new DefaultHapiContext();
			context.setValidationContext(ValidationContextFactory.defaultValidation());
			PipeParser parser = context.getPipeParser();

			try {

				context.getParserConfiguration().setValidating(false);
				Parser p = context.getGenericParser();
				Message hapiMsg = p.parse(msg);

				Terser terser = new Terser(hapiMsg);

				// ------------ MSH Fields ------------------------------

				String messageControlId = terser.get("/.MSH-10-1");

				hl7 datax = hl7Repo.findByMessageControlId2(messageControlId);
				Long serial = datax.getSerial();

				List<hl7FullOnly> data = hl7HistoryRepo.historyHl7(serial);

				List<String> list = new ArrayList<>();
				for (hl7FullOnly hl7 : data) {
					String msg1 = (hl7.getHl7full());

					String[] MSHSegment = msg1.split("(?<=PID)");
					String[] MSHSegmentSeparated = MSHSegment[0].split(Pattern.quote("|"));
					String RecivedApplication = MSHSegmentSeparated[2];
					String RecivedFacility = MSHSegmentSeparated[3];
					MSHSegmentSeparated[2] = "MPI";
					MSHSegmentSeparated[3] = "SLIT";

					MSHSegmentSeparated[4] = RecivedApplication;
					MSHSegmentSeparated[5] = RecivedFacility;

					DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
					Date date = new Date();
					MSHSegmentSeparated[6] = dateFormat.format(date);

					String joinedMSH = String.join("|", MSHSegmentSeparated);
					String fullMsg = joinedMSH + "\r\n" + MSHSegment[1];

					Collections.addAll(list, fullMsg);
				}

				// log add
				loghl7 log = new loghl7();
				log.setHl7full(msg);
				log.setApi("history");
				log.setStatus("success");
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				log.setCdate(datetime.format(now));
				log.setCby(apiKey);

				logHl7Repo.save(log);

				jsonObject.put("Response", list);
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			} catch (HL7Exception e) {

				// log add
				loghl7 log = new loghl7();
				log.setHl7full(msg);
				log.setApi("history");
				log.setStatus(e.toString());
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				log.setCdate(datetime.format(now));
				log.setCby(apiKey);

				logHl7Repo.save(log);

				jsonObject.put("Response", e);
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			}
		} else {

			// log add
			loghl7 log = new loghl7();
			log.setHl7full(msg);
			log.setApi("history");
			log.setStatus("Invalid Api or Institute Name");
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			log.setCdate(datetime.format(now));
			log.setCby(apiKey);

			logHl7Repo.save(log);

			jsonObject.put("Response", "Invalid Api or Institute Name");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}
	}

	@PostMapping("/validate")
	public ResponseEntity<?> validateHL7(@RequestBody hl7send hl7data) throws JSONException {

		String msg = hl7data.getHl7full();
		String apiKey = hl7data.getApikey();
		String instituteName = hl7data.getInstitutename();

		JSONObject jsonObject = new JSONObject();

		if (apiKey == null || apiKey == "") {
			jsonObject.put("Response", "Api key can't be empty");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

		if (instituteName == null || instituteName == "") {
			jsonObject.put("Response", "Institute name can't be empty");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

		Integer countA = hl7Repo.checkApiKey(apiKey, instituteName);
		if (countA == 1) {

			HapiContext context = new DefaultHapiContext();
			context.setValidationContext(ValidationContextFactory.defaultValidation());
			PipeParser parser = context.getPipeParser();

			// log add
			loghl7 log = new loghl7();
			log.setHl7full(msg);
			log.setApi("validate");

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			log.setCdate(datetime.format(now));

			try {
				parser.parse(msg);

				log.setStatus("success");
				log.setCby(apiKey);

				logHl7Repo.save(log);

				jsonObject.put("Response", "Successfully parsed valid message!");
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			} catch (HL7Exception e) {

				log.setStatus(e.toString());
				log.setCby(apiKey);

				logHl7Repo.save(log);

				jsonObject.put("Response", e);
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
			}
		} else {

			// log add
			loghl7 log = new loghl7();
			log.setHl7full(msg);
			log.setApi("validate");
			log.setStatus("Invalid Api or Institute Name");
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter datetime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			log.setCdate(datetime.format(now));
			log.setCby(apiKey);

			logHl7Repo.save(log);

			jsonObject.put("Response", "Invalid Api or Institute Name");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
		}

	}


}
