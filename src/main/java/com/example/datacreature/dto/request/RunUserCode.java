package com.example.datacreature.dto.request;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RunUserCode {
    private String userCode;
    private String input;
    private String output;
}
