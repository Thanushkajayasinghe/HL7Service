package com.mpi.hl7.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpi.hl7.model.loghl7;

public interface LogHl7Repo extends JpaRepository<loghl7, Long> {

}
