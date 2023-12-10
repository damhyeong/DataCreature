package com.example.datacreature.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Example")
@Getter
@Setter
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "example_number")
    private int exampleNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "level")
    private int level;

    @Column(name = "favorite_count")
    private int favoriteCount;

    @Column(name = "view_count")
    private int viewCount;

    @Column(name = "write_datetime")
    private Date writeDatetime;

    @Column(name = "write_nickname")
    private String writeNickname;

    // ExampleSource Table과 1:1 매칭 및, ID가 같으므로, 매핑하는 과정
    @JsonIgnore
    @OneToOne(mappedBy = "example", cascade = CascadeType.ALL)
    private ExampleSource exampleSource;
}