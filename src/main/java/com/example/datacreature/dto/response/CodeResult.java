package com.example.datacreature.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CodeResult {
    private boolean runResult;
    private String reason;
}
