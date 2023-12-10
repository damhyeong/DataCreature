package com.example.datacreature.dto.request;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ExecuteCodeRequest {
    private String userCode;
}
