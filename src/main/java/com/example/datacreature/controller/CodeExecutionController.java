package com.example.datacreature.controller;

import com.example.datacreature.service.DockerService;
import com.github.dockerjava.api.DockerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/docker")
@CrossOrigin(origins = "http://localhost:3000")
public class CodeExecutionController {
    public DockerService dockerService;

    @Autowired
    public CodeExecutionController(DockerService dockerService){
        this.dockerService = dockerService;
    }

    @PostMapping("/execute-code")
    public ResponseEntity<String> executeUserCode(@RequestBody String userCode){
        try{
            this.dockerService.setDockerClient(this.dockerService.createDockerClient());
            String result = dockerService.executeCode(userCode);
            return ResponseEntity.ok(result);
        } catch (InterruptedException e){
            return ResponseEntity.internalServerError().body("Error executing code: " + e.getMessage());
        }
    }


}
