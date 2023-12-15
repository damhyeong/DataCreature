package com.example.datacreature.controller;

import com.example.datacreature.dto.request.ExecuteCodeRequest;
import com.example.datacreature.dto.request.RunUserCode;
import com.example.datacreature.dto.response.CodeResult;
import com.example.datacreature.service.CodeExecutionService;
import com.example.datacreature.service.DockerService;
import com.github.dockerjava.api.DockerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/code-execute")
@CrossOrigin(origins = "http://localhost:3000")
public class CodeExecutionController {
    // public final DockerService dockerService;
    public final CodeExecutionService codeExecutionService;

    @Autowired
    public CodeExecutionController(CodeExecutionService codeExecutionService){
        this.codeExecutionService = codeExecutionService;
    }

    /*@PostMapping("/execute-code")
    public ResponseEntity<?> executeUserCode(@RequestBody ExecuteCodeRequest userCode){
        System.out.println("executeUserCode(@RequestBody String userCode) 실행");

        String code = userCode.getUserCode();
        System.out.println("userCode : ");
        System.out.println(code);
        try{
            DockerClient dockerClient = this.dockerService.createDockerClient();
            this.dockerService.setDockerClient(dockerClient);
            String result = dockerService.executeCode(code);
            return ResponseEntity.ok(result);
        } catch (InterruptedException e){
            return ResponseEntity.internalServerError().body("Error executing code: " + e.getMessage());
        }
    }*/

    @GetMapping("/test-run-code")
    public void executeUserCode(){
        System.out.println("executeUserCode() 실행됨 : Controller 받음");
        codeExecutionService.testRunUserCode();
    }
    @PostMapping("/test-run-code")
    public ResponseEntity<CodeResult> executingUserCode(@RequestBody RunUserCode runUserCode){
        System.out.println("executingUserCode() Post 실행됨");
        CodeResult codeResult = codeExecutionService.runUserCode(runUserCode.getUserCode(), runUserCode.getInput(), runUserCode.getOutput());

        return ResponseEntity.ok(codeResult);
    }
}
