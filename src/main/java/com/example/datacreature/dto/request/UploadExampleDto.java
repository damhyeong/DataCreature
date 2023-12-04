package com.example.datacreature.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UploadExampleDto {
    private String title;
    private Integer level;
    private String introduce;
    private String constraints;
    private String examInput;
    private String examOutput;
}
