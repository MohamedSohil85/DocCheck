package docCheck.persistence;

import docCheck.entity.DiseaseSymptomsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiseaseSymptomsInfoRepository extends JpaRepository<DiseaseSymptomsInfo,Long> {

    Optional<DiseaseSymptomsInfo> findByDisease(String disease);
    List<DiseaseSymptomsInfo> findByDiseaseContainingIgnoreCase(String keyword);
    List<DiseaseSymptomsInfo> findByRiskFactorsContainingIgnoreCase(String riskFactor);
    List<DiseaseSymptomsInfo> findBySymptomsContainingIgnoreCase(String symptom);

    //  Find diseases by overview or causes (search both fields)
    @Query("SELECT d FROM DiseaseSymptomsInfo d " +
            "WHERE LOWER(d.overview) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "   OR LOWER(d.Causes) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<DiseaseSymptomsInfo> searchByOverviewOrCauses(@Param("keyword") String keyword);
    // 7. Full text-like search across multiple fields
    @Query("SELECT d FROM DiseaseSymptomsInfo d " +
            "WHERE LOWER(d.disease) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "   OR LOWER(d.symptoms) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "   OR LOWER(d.Causes) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "   OR LOWER(d.riskFactors) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<DiseaseSymptomsInfo> searchAllFields(@Param("keyword") String keyword);

}
