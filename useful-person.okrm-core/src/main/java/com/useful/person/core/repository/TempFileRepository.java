package com.useful.person.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.useful.person.core.domain.TempFile;

public interface TempFileRepository extends JpaRepository<TempFile, String> {

}
