package com.doccheck.agents.DrugAgent.persistence;

import com.doccheck.agents.DrugAgent.entity.DrugsDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugsDescriptionRepository extends JpaRepository<DrugsDescription, Long> {
}
