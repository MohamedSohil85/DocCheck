package com.doccheck.agents.DrugAgent.persistence;

import com.doccheck.agents.DrugAgent.entity.DrugsBank;
import com.doccheck.agents.PatientAgent.entity.DocCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugsRepository extends JpaRepository<DrugsBank, Long> {

}
