package com.doccheck.agents.PatientAgent.entity;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class DocCheck implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CsvBindByName(column = "Question")
    @Column(columnDefinition = "TEXT",length = 1000)
    private String question;
    @CsvBindByName(column = "Answer")
    @Column(columnDefinition = "TEXT",length = 1000)
    private String answer;
    @CsvBindByName(column = "source")
    private String source;
    @CsvBindByName(column = "focus_area")
    private String focusArea;


}
