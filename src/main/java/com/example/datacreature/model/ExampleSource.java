package com.example.datacreature.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Example_Source")
@Getter
@Setter
public class ExampleSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_number")
    private int examNumber;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "exam_constraints")
    private String examConstraints;

    @Column(name = "exam_input")
    private String examInput;

    @Column(name = "exam_output")
    private String examOutput;

    @OneToOne
    @MapsId
    @JoinColumn(name = "exam_number")
    private Example example;
}
