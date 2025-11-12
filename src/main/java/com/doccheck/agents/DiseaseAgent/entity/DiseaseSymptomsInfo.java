package com.doccheck.agents.DiseaseAgent.entity;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString

    public class DiseaseSymptomsInfo implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @CsvBindByName(column = "disease")
        private String disease;
        @CsvBindByName(column = "link")
        private String link;
        @Column(columnDefinition="TEXT", length = 1000)
        @CsvBindByName(column = "Symptoms")
        private String symptoms;
        @Column(columnDefinition="TEXT", length = 1000)
        @CsvBindByName(column = "Overview")
        private String overview;
        @Column(columnDefinition="TEXT", length = 1000)
        @CsvBindByName(column = "Causes")
        private String Causes;
        @Column(columnDefinition="TEXT", length = 1000)
        @CsvBindByName(column = "Risk factors")
        private String riskFactors;
        @Column(columnDefinition="TEXT", length = 1000)
        @CsvBindByName(column = "diagnosis")
        private String diagnosis;
        @Column(columnDefinition="TEXT", length = 1000)
        @CsvBindByName(column = "treatment")
        private String treatment;
        @CsvBindByName(column = "medication")
        @Column(columnDefinition = "TEXT", length = 1000)
        private String medication;
        @Column(columnDefinition="TEXT", length = 1000)
        @CsvBindByName(column = "remedies")
        private String remedies;
    }

