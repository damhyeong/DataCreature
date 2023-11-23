package com.example.datacreature.dto.object;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;

public class ExampleListItem {
    private int exampleNumber; // 문제 ID
    private String title; // 문제 제목
    private String content; //
    private JSONObject expList;
    private int favoriteCount;
    private int viewCount;
    private String writeDatetime;
    private String writeNickname;
}
