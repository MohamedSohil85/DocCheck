package docCheck.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PatientReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportId;

    private String patientName;
    private LocalDate dateOfBirth;
    private String patientId;
    private String address;
    private String email;
    private LocalDate reportDate;
    private String physician;
    private String allergies;
    @ElementCollection
    private List<String> medicalHistory;
    @ElementCollection
    private List<String> currentMedications;
    @ElementCollection
    private List<String> reportedSymptoms;
    @OneToMany(targetEntity = LabResults.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id",referencedColumnName = "reportId")
    private List<LabResults> labResults;
    private LocalDateTime dateOfDiagnosis;
    private String diagnosis;
    @ElementCollection
    private List<String> treatmentPlan;
    @Column(columnDefinition = "TEXT",length = 1000)
    private String notes;

}

