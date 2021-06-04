package com.mpi.hl7.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mpi.hl7.model.hl7history;
import com.mpi.hl7.repository.HL7Repository.hl7FullOnly;

public interface Hl7HistoryRepo extends JpaRepository<hl7history, Long> {

	@Query(value = "SELECT * FROM hl7history WHERE serial=?1", nativeQuery = true)
	List<hl7FullOnly> historyHl7(Long serial);

}
