package com.example.datacreature.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExampleSourceListItem {
    private int exam_number;
    private String introduce;
    private String exam_constraints;
    private String exam_input;
    private String exam_output;
}
