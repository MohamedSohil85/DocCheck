package docCheck.entity;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DrugsInteraction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CsvBindByName(column = "Drug 1")
    private String firstDrug;
    @CsvBindByName(column = "Drug 2")
    private String secondDrug;
    @CsvBindByName(column = "Interaction Description")
    private String interactionDescription;
}
