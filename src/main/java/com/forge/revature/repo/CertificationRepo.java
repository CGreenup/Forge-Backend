package com.forge.revature.repo;

import com.forge.revature.models.Certification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CertificationRepo extends JpaRepository<Certification, Long>{
}
