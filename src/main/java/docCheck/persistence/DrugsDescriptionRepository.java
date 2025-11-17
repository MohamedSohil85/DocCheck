package docCheck.persistence;

import docCheck.entity.DrugsDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugsDescriptionRepository extends JpaRepository<DrugsDescription, Long> {
}
