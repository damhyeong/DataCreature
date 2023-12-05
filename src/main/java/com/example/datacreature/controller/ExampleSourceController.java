package com.example.datacreature.controller;

import com.example.datacreature.dto.request.UploadExampleDto;
import com.example.datacreature.model.Example;
import com.example.datacreature.model.ExampleSource;
import com.example.datacreature.service.ExampleService;
import com.example.datacreature.service.ExampleSourceService;
import com.example.datacreature.service.UploadExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examples")
@CrossOrigin(origins = "http://localhost:3000")
public class ExampleSourceController {
    private final ExampleSourceService examSrcService;
    private final ExampleService examService;
    private final UploadExampleService uploadExampleService;

    @Autowired
    public ExampleSourceController(ExampleSourceService examSrcService, ExampleService examService, UploadExampleService uploadExampleService) {
        this.examSrcService = examSrcService;
        this.examService = examService;
        this.uploadExampleService = uploadExampleService;
    }


    @GetMapping("/{id}")
    public ExampleSource getExampleSourceById(@PathVariable int id) {
        System.out.println("요청이 받아들여짐. : ExampleSource");
        return examSrcService.getExampleSourceById(id);
    }

    @GetMapping("/currentList")
    public List<Example> getExampleSourceByCurrent(){
        System.out.println("최신 문제 리스트 요청이 받아들여짐 : getExampleSourceByCurrent()");
        return examService.getAllExample();
    }

    @PostMapping("/examUpload")
    public ResponseEntity<UploadExampleDto> uploadExample(@RequestBody UploadExampleDto uploadExampleDto){
        System.out.println("UploadExample 요청 받아들여짐.");

        UploadExampleDto savedUploadExampleDto = uploadExampleService.createExample(uploadExampleDto);
        return ResponseEntity.ok(savedUploadExampleDto);
    }

}
