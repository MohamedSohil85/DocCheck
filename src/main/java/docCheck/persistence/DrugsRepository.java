package docCheck.persistence;

import docCheck.entity.DrugsBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugsRepository extends JpaRepository<DrugsBank, Long> {

}
