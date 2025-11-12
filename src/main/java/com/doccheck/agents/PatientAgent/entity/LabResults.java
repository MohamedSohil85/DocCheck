package com.doccheck.agents.PatientAgent.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class LabResults implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String testName;         // z. B. "Hämoglobin"
    private String value;            // z. B. "13.5"
    private String unit;             // z. B. "g/dL"
    private String referenceRange;   // z. B. "12.0 - 16.0"
    private String status;           // z. B. "normal", "hoch", "niedrig"
    private String comment;
}
