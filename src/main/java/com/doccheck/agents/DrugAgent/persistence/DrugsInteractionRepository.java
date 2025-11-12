package com.doccheck.agents.DrugAgent.persistence;

import com.doccheck.agents.DrugAgent.entity.DrugsInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugsInteractionRepository extends JpaRepository<DrugsInteraction, Long> {
}
