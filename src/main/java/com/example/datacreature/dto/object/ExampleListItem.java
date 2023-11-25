package com.example.datacreature.dto.object;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExampleListItem {
    private int exampleNumber; // 문제 ID
    private String title; // 문제 제목
    private int level; //
    private int favoriteCount;
    private int viewCount;
    private String writeDatetime;
    private String writeNickname;
}