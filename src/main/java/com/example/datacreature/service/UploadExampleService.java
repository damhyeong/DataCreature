package com.example.datacreature.service;

import com.example.datacreature.dto.request.UploadExampleDto;
import com.example.datacreature.model.Example;
import com.example.datacreature.model.ExampleSource;
import com.example.datacreature.repository.ExampleRepository;
import com.example.datacreature.repository.ExampleSourceRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatterBuilder;

@Service
public class UploadExampleService {
    private final ExampleService exampleService;
    private final ExampleSourceService exampleSourceService;

    public UploadExampleService(ExampleService exampleService, ExampleSourceService examSrcService){
        this.exampleService = exampleService;
        this.exampleSourceService = examSrcService;
    }

    public UploadExampleDto createExample(UploadExampleDto uploadExampleDto){

        // Example Table에 넣을 Record 정보 생성
        Example example = new Example();
        example.setTitle(uploadExampleDto.getTitle());
        example.setLevel(uploadExampleDto.getLevel());
        example.setViewCount(0);
        example.setFavoriteCount(0);

        // 현재 시간 가져오기
        LocalDateTime now = LocalDateTime.now();
        Date currentDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        example.setWriteDatetime(currentDate);

        //나중에 닉네임 가져와서 등록
        example.setWriteNickname("user1");

        // 먼저 Example Table에 레이블을 등록한다.
        example = exampleService.createExample(example);


        // nickname은 나중에 uploadExampleDto.getNickname으로 할 수 있도록 만들어야 한다.
        // null이 나올수도 있다.
//        Integer example_number = exampleService.findByTitleAndNickname(uploadExampleDto.getTitle(), "user1");

        // ExampleSource Table에 Record를 넣기 위한 정보.
        ExampleSource exampleSource = new ExampleSource();

        // Example Table에서 Title과 Nickname으로 가져온 id를 넣는다.
//        exampleSource.setExamNumber(example_number);
//        System.out.println("example_number : " + example_number);
        exampleSource.setIntroduce(uploadExampleDto.getIntroduce());
        exampleSource.setExamConstraints(uploadExampleDto.getConstraints());
        exampleSource.setExamInput(uploadExampleDto.getExamInput());
        exampleSource.setExamOutput(uploadExampleDto.getExamOutput());

        exampleSource.setExample(example);

        // 문제 상세 정보에 레코드 정보 넣기.
        exampleSourceService.createExampleSource(exampleSource);

        return uploadExampleDto;
    }
}
